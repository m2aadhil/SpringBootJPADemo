package com.xchange.demobackend.infastructure.product;

import com.xchange.demobackend.infastructure.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrue();
}
