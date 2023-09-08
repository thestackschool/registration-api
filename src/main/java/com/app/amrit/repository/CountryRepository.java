package com.app.amrit.repository;

import com.app.amrit.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable> {
}
