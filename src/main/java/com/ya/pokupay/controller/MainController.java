package com.ya.pokupay.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Image;
import com.ya.pokupay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

@Controller
public class MainController {

    private static final Map<String, String> categories;
    static
    {
        categories = new HashMap<String, String>();
        categories.put("all", "Все категории");
        categories.put("transport", "Транспорт");
        categories.put("property", "Недвижимость");
        categories.put("businessAndServices", "Бизнес и услуги");
        categories.put("electronics", "Электроника");
        categories.put("fashionAndStyle", "Мода и стиль");
        categories.put("recreationAndSports", "Отдых и спорт");
        categories.put("work", "Работа");
        categories.put("animals", "Животные");
        categories.put("childsWorld", "Детский мир");
        categories.put("technics", "Техника");
    }

    @Resource(name="generalProperties")
    private Properties generalProperties;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value =  "/", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        String category = "Все категории";
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        return "index";
    }

    @RequestMapping(value = "/{category}", method = RequestMethod.GET)
    public String categoryPage(@PathVariable("category") String category, Model model) {
        String queryCategory = categories.get(category);
        if (queryCategory == null) {
            queryCategory = "Все категории";
        }

        model.addAttribute("category", queryCategory);
        model.addAttribute("categories", categories);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value =  "/getAdverts/{category}/{orderByCriteria}/{user}", method = RequestMethod.GET)
    public List<Advert> getAdverts(@PathVariable("category") String category,
                                   @PathVariable("orderByCriteria") String orderByCriteria,
                                   @PathVariable("user") String user) throws IOException {
        List<Advert> advertList = this.advertService.listAdverts(category, orderByCriteria, user);
        return advertList;
    }

    @RequestMapping(value = "/obyavleniye/{advertid}", method = RequestMethod.GET)
    public String userPage(@PathVariable("advertid") Integer advertid, Model model) {
        Advert advert = advertService.getAdvertById(advertid);
        model.addAttribute("advert", advert);

        List<Image> images = imageService.getImagesByAdvertId(advertid);

        if (images != null) {
            List<String> imagesNames = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                imagesNames.add(images.get(i).getName());
            }
            model.addAttribute("imagesNames", imagesNames);
        } else {
            model.addAttribute("imageNotFound", true);
        }
        model.addAttribute("user", userService.findByUsername(advert.getAuthorUsername()));
        return "advertPage";
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(HttpServletResponse response, HttpServletRequest request, Model model,
                          @RequestParam("advertId") Integer advertId,
                          @RequestParam("user") String user,
                          @RequestParam("fileName") String fileName)
            throws ServletException, IOException, SQLException {
        String imagesUrl = generalProperties.getProperty("imagesUrl");
        if (!fileName.equals("")) {
            File file = new File(imagesUrl + user + "/" + advertService.getAdvertById(advertId).getTitle() + "/" + fileName);
            InputStream inputStream = null;
            inputStream = new FileInputStream(file);
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } else {
            File file = new File(imagesUrl + user + "/" + advertService.getAdvertById(advertId).getTitle() + "/" + imageService.getOneImageByAdvertId(advertId));
            InputStream inputStream = null;
            inputStream = new FileInputStream(file);
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @RequestMapping(value = "/increaseViewCount/{advertid}", method = RequestMethod.POST)
    public void viewCounter(@PathVariable("advertid") Integer advertid) {

        Advert advert = advertService.getAdvertById(advertid);
        advertService.increaseViewCounter(advert);
//        return "viewCounterIncreased";
    }

    @RequestMapping(value =  "/addAdvert", method = RequestMethod.GET)
    public String addAdvertPage(Model model) {
        model.addAttribute("advert", new Advert());
        Map<String, String> newCategories =  new HashMap<>(categories);
        newCategories.remove("all");
        model.addAttribute("categories", newCategories);
        return "addAdvert";
    }

    @ResponseBody
    @RequestMapping(value="/saveAdvert", method = RequestMethod.POST)
    public String saveAdvertPage (MultipartHttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        Advert advert = null;
        try {
            advert = mapper.readValue(request.getParameter("advert"), Advert.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");
        Integer advertid = this.advertService.addAdvert(advert).getId();

        List<MultipartFile> files = request.getFiles("files");
        return imageService.save(files, advertid);
//        return null;
    }

    @ResponseBody
    @RequestMapping(value="/deleteAdvert/{advertId}", method = RequestMethod.GET)
    public String deleteAdvertPage (@PathVariable("advertId") Integer advertId) {
        String resultDelImg = this.imageService.delete(advertId);
        String result = this.advertService.deleteAdvert(advertId);
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMyException(Exception  exception) {
        return "admin";
    }

    @ResponseBody
    @RequestMapping(value="/search/{searchQuery}/{category}", method = RequestMethod.GET)
    public List<Advert> searchResult (@PathVariable("searchQuery") String searchQuery, @PathVariable("category") String category) throws InterruptedException {
        List<Advert> advertList = this.advertService.searchAdvert(searchQuery, category);
        return advertList;
    }
}
