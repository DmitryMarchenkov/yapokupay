package com.ya.pokupay.service;

import com.ya.pokupay.dao.ImageDAO;
import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private ImageDAO imageDAO;

    @Autowired
    private AdvertService advertService;

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public String save(List<MultipartFile> images, Integer advertid) {
        List<Image> imageList = new ArrayList<>();
        if (images.size() != 0) {
            for (int i = 0; i < images.size(); i++) {
                try {
                    MultipartFile multiFile = images.get(i);
                    String filename = multiFile.getOriginalFilename();

                    Image image = new Image();
                    image.setName(filename);
                    image.setAdvertid(advertid);
                    Advert advert = advertService.getAdvertById(advertid);
                    image.setTitle(advert.getTitle());
                    image.setUser(advert.getAuthorUsername());
                    image.setFile(multiFile);
                    imageList.add(image);
                } catch (Exception e) {
                    System.out.println("exception: " + e);
                }
            }
        }

        return this.imageDAO.save(imageList);
    }

    @Override
    public String delete(Integer advertId) {
        Advert advert = advertService.getAdvertById(advertId);
        if (advert != null) {
            return this.imageDAO.delete(advert.getId(), advert.getAuthorUsername(), advert.getTitle());
        }
        return "directory does not exist!";
    }

    @Override
    public List<Image> getImagesByAdvertId(Integer id) {
        List<Image> imageList = this.imageDAO.getImagesList(id);
        if (imageList.isEmpty()) return null;
        return imageList;
    }

    @Override
    public String getOneImageByAdvertId(Integer id) {
        String image = null;
        try {
            image = this.imageDAO.getOneImageByAdvertId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public Image getImageById(Integer id) {
        return this.imageDAO.getImageById(id);
    }
}
