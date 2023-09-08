package com.app.amrit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
@Table(name = "STATE_MASTER")
public class StateEntity {

    @Id
    @Column(name = "STATE_ID")
    private Integer stateId;

    @Column(name = "STATE_NAME")
    private String stateName;

    @Column(name = "COUNTRY_ID")
    private Integer countryId;
}