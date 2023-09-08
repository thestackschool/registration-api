package com.app.amrit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
@Table(name = "CITY_MASTER")
public class CityEntity {

    @Id
    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "STATE_ID")
    private Integer stateId;
}