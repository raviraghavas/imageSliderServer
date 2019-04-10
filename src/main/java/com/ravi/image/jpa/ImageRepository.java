package com.ravi.image.jpa;

import org.springframework.data.repository.CrudRepository;

import com.ravi.image.entity.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

}
