package com.example.shop.repos;

import com.example.shop.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String text);
    List<Message> findById(int text);


    @Override
    void deleteById(Integer integer);
}