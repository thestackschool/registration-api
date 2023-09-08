package com.app.amrit.repository;

import com.app.amrit.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Serializable> {

    public List<CityEntity> findByStateId(Integer stateId);
}
