package com.xchange.demobackend.infastructure.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xchange.demobackend.infastructure.product.model.Product;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Transaction {

    @Id
    @Column(name = "t_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "t_code", nullable = false, unique=true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String transactionCode;

    @Column(name = "t_user", nullable = false)
    private String user;

    @Column(name = "t_price", nullable = false)
    private BigDecimal price;

    @Column(name = "t_tax")
    private BigDecimal tax;

    @Column(name = "p_createdAt", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "p_modifiedAt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToMany
    @JoinTable(
            name = "transaction_products",
            joinColumns = @JoinColumn(name = "t_id"),
            inverseJoinColumns = @JoinColumn(name = "p_id"))
    private List<Product> products;

    public Transaction(String user, BigDecimal price, BigDecimal tax, List<Product> products) {
        this.user = user;
        this.price = price;
        this.tax = tax;
        this.products = products;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public String getUser() {
        return user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
