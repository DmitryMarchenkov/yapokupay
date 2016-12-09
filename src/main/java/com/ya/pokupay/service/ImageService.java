package com.ya.pokupay.service;

import com.ya.pokupay.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void save(List<MultipartFile> images, Integer advertid);

    List<Image> getImagesByAdvertId(Integer id);

    Image getOneImageByAdvertId(Integer id);
}
