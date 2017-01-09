package com.ya.pokupay.service;

import com.ya.pokupay.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    String save(List<MultipartFile> images, Integer advertid);

    String delete(Integer advertId);

    List<Image> getImagesByAdvertId(Integer id);

    String getOneImageByAdvertId(Integer id) throws SQLException;

    Image getImageById(Integer id);
}
