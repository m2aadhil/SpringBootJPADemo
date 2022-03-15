package com.xchange.demobackend.domain.transaction.core;

import com.xchange.demobackend.application.exception.InventoryNotAvailableException;
import com.xchange.demobackend.application.exception.ItemNotFoundException;
import com.xchange.demobackend.domain.product.IProductService;
import com.xchange.demobackend.domain.transaction.ITransactionService;
import com.xchange.demobackend.infastructure.dto.ProductsWthQty;
import com.xchange.demobackend.infastructure.dto.TransactionDTO;
import com.xchange.demobackend.infastructure.product.model.Product;
import com.xchange.demobackend.infastructure.transaction.ITransactionRepository;
import com.xchange.demobackend.infastructure.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IProductService productService;

    public String processTransaction(TransactionDTO transaction) throws ItemNotFoundException {

        List<ProductsWthQty> productsWithQty = transaction.products;

        List<Product> productsData = productService.getProducts(productsWithQty.stream().map(ProductsWthQty::getProductId).collect(Collectors.toList()));

        BigDecimal totalPrice = new BigDecimal(0);

        for (Product product: productsData) {
            Integer qty = (productsWithQty.stream().filter(productsWthQty -> product.getId().equals(productsWthQty.getProductId())).findAny().orElse(null)).getQty();

            //validating the purchase quantity with the remaining amount
            if(product.getRemainingQuantity() >= qty){

                //reducing the amount from the remaining
                product.setRemainingQuantity(product.getRemainingQuantity() - qty);

                //calculating total
                totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(qty)));

            }else{
                throw new InventoryNotAvailableException(product.getProductCode());
            }
        }

        productService.creatProducts(productsData);

        Transaction completedTransaction = transactionRepository.save(new Transaction(transaction.user, totalPrice, new BigDecimal(0), productsData));

        return completedTransaction != null ? completedTransaction.getTransactionCode() : null;
    }
}
