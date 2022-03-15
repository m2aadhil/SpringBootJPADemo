package com.xchange.demobackend.domain.product.core;

import com.xchange.demobackend.domain.product.IProductService;
import com.xchange.demobackend.infastructure.product.model.Product;
import com.xchange.demobackend.infastructure.product.IProductsRepository;
import com.xchange.demobackend.application.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductsRepository productRepository;

    public ProductService() {
    }

    public Product creatProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> creatProducts(List<Product> products){
        return this.productRepository.saveAll(products);
    }

    public Product creatProduct(long id, Product product) throws ItemNotFoundException {
        Product productUpdate = this.productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product", "id", id));

        if(product.getProductCode() != null){
            productUpdate.setProductCode(product.getProductCode());
        }

        if(product.getName() != null){
            productUpdate.setName(product.getName());
        }

        if(product.getPrice() !=  null){
            productUpdate.setPrice(product.getPrice());
        }

        if(product.getRemainingQuantity() != null){
            productUpdate.setRemainingQuantity(product.getRemainingQuantity());
        }

        return productRepository.save(productUpdate);
    }

    public Product getProduct(long id) throws ItemNotFoundException{
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product", "id", id));
        return product;
    }

    public List<Product> getProducts(){
        return this.productRepository.findByIsActiveTrue();
    }

    public List<Product> getProducts(List<Long> ids){
        return this.productRepository.findAllById(ids);
    }

    public boolean deleteProduct(long id) throws ItemNotFoundException{
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product", "id", id));
        product.setActive(false);
        return productRepository.save(product) != null ? true: false;
    }

}
