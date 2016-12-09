package com.ya.pokupay.dao;

import com.ya.pokupay.model.Image;

import java.io.InputStream;
import java.util.List;

public interface ImageDAO {
    void save(List<Image> image);

    List<Image> getImagesList(Integer id);

    Image getOneImage(Integer id);
}
