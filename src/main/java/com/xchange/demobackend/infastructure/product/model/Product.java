package com.xchange.demobackend.infastructure.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xchange.demobackend.infastructure.transaction.model.Transaction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Product {
    @Id
    @Column(name = "p_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "p_code", nullable = false, unique=true)
    private String productCode;

    @Column(name = "p_name", nullable = false)
    private String name;

    @Column(name = "p_qty", nullable = false)
    private Integer remainingQuantity;

    @Column(name = "p_value", nullable = false)
    private BigDecimal price;

    @Column(name = "p_createdAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "p_modifiedAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @ManyToMany(mappedBy = "products")
    private List<Transaction> transactions;

    public Product() {
        this.isActive = true;
    }

    public Product(String productCode, String name, Integer remainingQuantity, BigDecimal price) {
        this.productCode = productCode;
        this.name = name;
        this.remainingQuantity = remainingQuantity;
        this.price = price;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Integer quantity) {
        this.remainingQuantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
