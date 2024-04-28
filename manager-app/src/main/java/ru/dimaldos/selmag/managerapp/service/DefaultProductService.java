package ru.dimaldos.selmag.managerapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dimaldos.selmag.managerapp.client.ProductsRestClient;
import ru.dimaldos.selmag.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductsRestClient productsRestClient;

    @Override
    public List<Product> findAllProducts(String filter) {
        return productsRestClient.findAllProducts(filter);
    }

    @Override
    public Product createProduct(String title, String details) {
        return this.productsRestClient.createProduct(title, details);
    }

    @Override
    public Optional<Product> findProduct(int productId) {
        return this.productsRestClient.findProduct(productId);
    }

    @Override
    public void updateProduct(Integer id, String title, String details) {
        this.productsRestClient.updateProduct(id, title, details);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productsRestClient.deleteProduct(id);
    }
}
