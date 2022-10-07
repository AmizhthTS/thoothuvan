package com.smsapi.masterdbapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smsapi.masterdbapi.model.RouteDefaultModel;


@Repository
public interface RouteDefaultDao extends JpaRepository<RouteDefaultModel, Integer> {

	RouteDefaultModel findByIdEquals(int id);


}