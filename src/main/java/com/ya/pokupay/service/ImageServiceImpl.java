package com.ya.pokupay.service;

import com.ya.pokupay.dao.ImageDAO;
import com.ya.pokupay.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private ImageDAO imageDAO;

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
                    byte[] bytes = multiFile.getBytes();
                    Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

                    Image image = new Image();
                    image.setName(filename);
                    image.setAdvertid(advertid);
                    image.setData(blob);
                    imageList.add(image);
//                    this.imageDAO.save(image);
                } catch (Exception e) {
                    System.out.println("exception: " + e);
                }
            }
        }

        return this.imageDAO.save(imageList);
    }

    @Override
    public List<Image> getImagesByAdvertId(Integer id) {
        List<Image> imageList = this.imageDAO.getImagesList(id);
        if (imageList.isEmpty()) return null;
        return imageList;
    }

    @Override
    public Image getOneImageByAdvertId(Integer id) {
        Image image = null;
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
