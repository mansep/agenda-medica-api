package com.mansep.agenda.repository;

import com.mansep.agenda.entity.Configuration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

}