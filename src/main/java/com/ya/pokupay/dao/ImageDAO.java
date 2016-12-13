package com.ya.pokupay.dao;

import com.ya.pokupay.model.Image;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public interface ImageDAO {
    String save(List<Image> image);

    List<Image> getImagesList(Integer id);

    Image getOneImageByAdvertId(Integer id) throws SQLException;

    Image getImageById(Integer id);
}
