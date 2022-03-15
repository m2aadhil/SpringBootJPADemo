package com.xchange.demobackend.application.controller;

import com.xchange.demobackend.application.exception.ItemNotFoundException;
import com.xchange.demobackend.infastructure.product.model.Product;
import com.xchange.demobackend.domain.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    ProductController(){
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id")String id){
        return ResponseEntity.ok(productService.getProduct(Long.valueOf(id)));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.creatProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id")Long id, @RequestBody Product product) throws ItemNotFoundException {
        return ResponseEntity.ok(productService.creatProduct(id, product));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value = "id")Long id) throws ItemNotFoundException{
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
