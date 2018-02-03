package com.spring.boot.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.spring.boot.dao.entity.FactEntity;

@Transactional
public interface FactorialRepository extends CrudRepository<FactEntity, Integer> {

}
