package com.example.product.service.query;

import com.example.product.service.core.ProductEntity;
import com.example.product.service.core.data.ProductRepository;
import com.example.product.service.query.rest.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {
    private final ProductRepository productRepository;
    public ProductQueryHandler(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @QueryHandler
    List<ProductRestModel> findProducts(FindProductsQuery query){

        List<ProductRestModel> productsRest = new ArrayList<>();
        List<ProductEntity> storedProducts = productRepository.findAll();
        for (ProductEntity productEntity : storedProducts) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productsRest.add(productRestModel);
        }
        return productsRest;

    }

}
