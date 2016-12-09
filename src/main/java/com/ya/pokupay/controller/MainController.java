package com.ya.pokupay.controller;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Image;
import com.ya.pokupay.model.User;
import com.ya.pokupay.service.*;
import com.ya.pokupay.validator.UserValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value =  {"/", "/all"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        String category = "Все категории";
        model.addAttribute("category", category);
        model.addAttribute("listAdverts", this.advertService.listAdverts(null));
        return "index";
    }

    @RequestMapping(value =  "/{category}", method = RequestMethod.GET)
    public String autoPage(@PathVariable("category") String category, Model model) {
        String queryCategory;
        switch (category) {
            case "auto":
                queryCategory = "Авто";
                break;
            case "technic":
                queryCategory = "Техника";
                break;
            case "property":
                queryCategory = "Недвижимость";
                break;
            case "clothes":
                queryCategory = "Одежда";
                break;
            default:
                queryCategory = "Все категории";
                break;
        }
        model.addAttribute("category", queryCategory);
        model.addAttribute("listAdverts", this.advertService.listAdverts(queryCategory));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    public String userPage(@PathVariable("user") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "userPage";
    }

    @RequestMapping(value = "/obyavleniye/{advertid}", method = RequestMethod.GET)
    public String userPage(@PathVariable("advertid") Integer advertid, Model model, HttpServletRequest request) {
        Advert advert = advertService.getAdvertById(advertid);
        model.addAttribute("advert", advert);
        List<Image> images = imageService.getImagesByAdvertId(advertid);
        model.addAttribute("images", images);


        List<Integer> imagesCount = Arrays.asList(new Integer[advert.getImagesCount()]);
        model.addAttribute("imagesCount", imagesCount);
//        Image image = images.get(0);
////        String url = "data:image/jpg;base64," + Base64.getEncoder().encode((ByteBuffer) image.getData());
//
//        Blob blob = image.getData();
//        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
////        byte[] bytes = image.getData().getBytes(1,);
//
//        byte[] encodeBase64 = Base64.getEncoder().encode(imageBytes);
//        String base64Encoded = new String(encodeBase64, "UTF-8");
//        image.setBase64imageFile(base64Encoded);



//        model.addAttribute("images", url);

//        model.addAttribute("catalinaHomeDir", System.getProperty("catalina.home"));
        return "advertPage";
    }

    @ResponseBody
    @RequestMapping(value = "/image/{authorUsername}/{title}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void testphoto(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable("authorUsername") String authorUsername,
                                         @PathVariable("title") String title) throws IOException {
       /* String contextPath = request.getSession().getServletContext().getRealPath("");
        String directory = contextPath.substring(0,contextPath.length() - 18) + "Resources/resources/uploadImages/" + authorUsername + "/" + title + "/0.jpg";
        String directory2 = "D:\\works\\YaPokupay\\src\\main\\webapp\\resources\\uploadImages\\" + authorUsername + "\\" + title + "\\0.jpg";
        String t = "classpath:com/resources/uploadImages/zeus192/Картинка/12.jpg";
        InputStream is = servletContext.getResourceAsStream("classpath:/com/resources/uploadImages/zeus192/Картинка/12.jpg");



//        ApplicationContext appContext =
//                new ClassPathXmlApplicationContext(new String[] {""});

//        URL resource = request.getSession().getServletContext().getResource("file:d:\\14.jpg");
//        URL resource2 = request.getSession().getServletContext().getResource("url:http://www.yourdomain.com/testing.txt");
//        URL resource3 = request.getSession().getServletContext().getResource("classpath:/com/resources/uploadImages/zeus192/Картинка/12.jpg");

//        InputStream in = servletContext.getResourceAsStream(directory);
//        ServletContext sercontext = request.getSession().getServletContext();
//        InputStream in = sercontext.getResourceAsStream("resources/uploadImages/zeus 192/Картинка/0.jpg");

        String path = "D:\\Works\\YaPokupay\\target\\YaPokupay\\resources\\uploadImages\\zeus192\\Красивые фото\\0.jpg";

//        URL url = new URL("/resources/uploadImages/zeus192/" + title + "/0.jpg");
        InputStream in = servletContext.getResourceAsStream("/resources/uploadImages/zeus192/" + title + "/0.jpg");
        InputStream in2 = servletContext.getResourceAsStream("/webapp/resources/uploadImages/zeus192/" + title + "/0.jpg");
        InputStream in3 = servletContext.getResourceAsStream("/../resources/uploadImages/zeus192/" + title + "/0.jpg");
        InputStream in4 = servletContext.getResourceAsStream("/resources/uploadImages/zeus192/" + title + "/0.jpg");

//        Resource resource = new ClassPathResource("uploadImages/zeus192/" + title + "/0.jpg");
//        InputStream resourceInputStream = resource.getInputStream();

//        InputStream in = servletContext.getResourceAsStream("/12.jpg");
        return IOUtils.toByteArray(in);

*/
//        servletContext.getResource("/resources/uploadImages/zeus192/‌​" + title + "/0.jpg").openStream();
//        new FileInputStream(new File(servletContext.getResource("/resources/uploadImages/zeu‌​s192/" + title + "/0.jpg").getPath()));

        String contextPath = request.getSession().getServletContext().getRealPath("");
        String directory = contextPath.substring(0,contextPath.length() - 18) + "/src/main/java/com/resources/uploadImages/" + authorUsername + "/" + title + "/0.jpg";

        String catalina = System.getProperty("catalina.home");
        File file = new File(directory);
        String filePath = file.getPath();
        InputStream instream22 = this.getClass().getClassLoader().getResourceAsStream("resources/uploadImages/" + authorUsername + "/" + title + "/0.jpg");
//        InputStream instream3 = this.getClass().getClassLoader().getResourceAsStream("/resources/uploadImages/zeus192/" + title + "/0.jpg");
        String sttt = "D:\\Works\\YaPokupay\\src\\main\\webapp\\resources\\uploadImages\\zeus192\\tot\\1.jpg";
        InputStream instream2 = servletContext.getResourceAsStream(sttt);
        InputStream instream3 = servletContext.getResourceAsStream(catalina);
        InputStream instream4 = servletContext.getResourceAsStream("resources/uploadImages/" + authorUsername + "/" + title + "/0.jpg");

        Path path = file.toPath();
        InputStream instream = Files.newInputStream(path);

        InputStream instream888 = servletContext.getResourceAsStream("/resources/uploadImages/zeus192/" + title + "/0.jpg");
        byte[] bytes =  IOUtils.toByteArray(instream);
        int contentLength = IOUtils.copy(new ByteArrayInputStream(bytes), response.getOutputStream());
        response.setContentLength(contentLength);

        if(true){  // if you want to attachment to be inline
            response.setHeader("Content-Disposition", "inline;filename=\"" + "0.jpg" + "\"");
        }else{
            response.setHeader("Content-Disposition", "attachment;filename=\"" + "0.jpg" + "\"");
        }

//        return bytes;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        String token = generateString(20);
        userForm.setToken(token);

        userService.save(userForm);

        System.out.println("EmailController email is called");
        Map<String, Object> modelEmail = new HashMap<>();
        modelEmail.put("from", "yapokupayfree@gmail.com");
        modelEmail.put("subject", "Подтверждение регистрации на Ya Pokupay!");
        modelEmail.put("to", userForm.getEmail());
        modelEmail.put("userName", userForm.getUsername());
        modelEmail.put("phone", userForm.getPhone());
        modelEmail.put("firstName", userForm.getFirstName());
        modelEmail.put("lastName", userForm.getLastName());
        modelEmail.put("token", token);
        boolean result = emailService.sendEmail("registered.vm", modelEmail);
        return "redirect:/all";
    }

    @RequestMapping(value = "/confirmRegistration/{username}/{token}", method = RequestMethod.GET)
    public String confirmRegistration(
            @PathVariable("username") String username,
            @PathVariable("token") String token,
            Model model,
            HttpServletRequest request) {

        User user = userService.findByUsername(username);
        if (user.getToken() != null) {
            if (user.getToken().equals(token)) {
                userService.confirmRegistration(username);
            }
        }
        securityService.autoLogin(user);
        return "confirmRegistration";
    }

    @RequestMapping(value = "/increaseViewCount/{advertid}", method = RequestMethod.POST)
    public String viewCounter(@PathVariable("advertid") Integer advertid) {

        Advert advert = advertService.getAdvertById(advertid);
        advertService.increaseViewCounter(advert);
        return "viewCounterIncreased";
    }

    public static String generateString(int length)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }




    @RequestMapping(value =  "/addAdvert", method = RequestMethod.GET)
    public String addAdvertPage(Model model) {
        model.addAttribute("advert", new Advert());
        return "addAdvert";
    }

    @ResponseBody
    @RequestMapping(value="/saveAdvert", method = RequestMethod.POST)
    public Advert uploadPage (@RequestBody Advert obyavleniye) {
        return this.advertService.addAdvert(obyavleniye);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMyException(Exception  exception) {
        return "admin";
    }

//    @ResponseBody
//    @RequestMapping(value="/uploadImages/{advertid}", method = RequestMethod.POST)
//    public String uploadImages (MultipartHttpServletRequest request,
//                                @PathVariable("advertid") Integer advertid) {
//        List<MultipartFile> files = request.getFiles("files");
//        imageService.save(files, advertid);
//        return "redirect:/all";
//    }

@ResponseBody
    @RequestMapping(value="/uploadImages/{user}/{title}", method = RequestMethod.POST)
    public String uploadImages (MultipartHttpServletRequest request,
                                @PathVariable("user") String username,
                                @PathVariable("title") String title) throws Exception {
        List<MultipartFile> files = request.getFiles("files");
        String contextPath = request.getSession().getServletContext().getRealPath("");
        File directory = new File(contextPath.substring(0,contextPath.length() - 17) + "/src/main/webapp/resources/uploadImages/" + username + "/" + title + "/");

//        String contextPath = System.getProperty("catalina.home");
//        File directory = new File(contextPath + "/resources/uploadImages/" + username + "/" + title + "/");


        for (int i = 0; i < files.size(); i++) {
            try {
                MultipartFile multiFile = files.get(i);
                String fileName = i + ".jpg";

                //making directories for our required path.
                byte[] bytes = multiFile.getBytes();
                directory.mkdirs();

                // saving the file
                File file = new File(directory + System.getProperty("file.separator") + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Error while loading the file");
            }
        }
//        return "success";
        return "redirect:/all";
    }



}
