package com.example.shop.repos;

import com.example.shop.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepo extends CrudRepository<Product, Integer> {

}
