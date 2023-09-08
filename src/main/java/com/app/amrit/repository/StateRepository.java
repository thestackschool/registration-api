package com.app.amrit.repository;

import com.app.amrit.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity, Serializable> {

    public List<StateEntity> findByCountryId(Integer countryId);
}
