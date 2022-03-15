package com.xchange.demobackend.domain.product;

import com.xchange.demobackend.infastructure.product.model.Product;

import java.util.List;

public interface IProductService {

    Product creatProduct(Product product);

    Product creatProduct(long id, Product product);

    Product getProduct(long id);

    List<Product> getProducts();

    List<Product> getProducts(List<Long> ids);

    boolean deleteProduct(long id);

    List<Product> creatProducts(List<Product> products);

}
