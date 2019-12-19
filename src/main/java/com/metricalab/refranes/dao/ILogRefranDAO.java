package com.metricalab.refranes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metricalab.refranes.entity.LogRefran;

public interface ILogRefranDAO extends JpaRepository<LogRefran, Long> {

}
