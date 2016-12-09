package com.ya.pokupay.service;

import com.ya.pokupay.dao.ImageDAO;
import com.ya.pokupay.model.Image;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ImageServiceImpl implements ImageService {

    private ImageDAO imageDAO;

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public void save(List<MultipartFile> images, Integer advertid) {
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
                } catch (Exception e) {
                    System.out.println("exception: " + e);
                }
            }
        }

        this.imageDAO.save(imageList);
    }

    @Override
    public List<Image> getImagesByAdvertId(Integer id) {
//        if (count != null) {
            List<Image> imageList = this.imageDAO.getImagesList(id);
//        } else {
//            List<Image> imageList = this.imageDAO.getOneImage(id);
//        }




        if (imageList.isEmpty()) return null;

//        try {
            for (int i = 0; i < imageList.size(); i++) {
//                Image image = imageList.get(i);
                convertImageDataToBase64(imageList.get(i));

//                Blob blob = image.getData();
//                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
//                byte[] encodeBase64 = Base64.getEncoder().encode(imageBytes);
//                String base64Encoded = new String(encodeBase64, "UTF-8");
//                image.setBase64imageFile(base64Encoded);

            }
//        } catch (Exception e) {
//            System.out.println("exception: " + e);
//        }
        return imageList;
    }

    @Override
    public Image getOneImageByAdvertId(Integer id) {
        Image image = this.imageDAO.getOneImage(id);
        if (image != null) {
            convertImageDataToBase64(image);
        }
        return image;
    }

    private Image convertImageDataToBase64(Image image) {
        try {
            Blob blob = image.getData();
            byte[] imageBytes = blob.getBytes(1, (int) blob.length());
            byte[] encodeBase64 = Base64.getEncoder().encode(imageBytes);
            String base64Encoded = new String(encodeBase64, "UTF-8");
            image.setBase64imageFile(base64Encoded);
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
        return image;
    }
}
