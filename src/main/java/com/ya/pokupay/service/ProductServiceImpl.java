package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ya.pokupay.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> listProducts() {
        return this.productDAO.listProducts();
    }
}
