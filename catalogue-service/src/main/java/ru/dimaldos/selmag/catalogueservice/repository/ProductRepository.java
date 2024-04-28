package ru.dimaldos.selmag.catalogueservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dimaldos.selmag.catalogueservice.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String filter);
}
