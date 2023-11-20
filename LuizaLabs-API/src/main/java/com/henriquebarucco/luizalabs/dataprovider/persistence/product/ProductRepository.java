package com.henriquebarucco.luizalabs.dataprovider.persistence.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
}
