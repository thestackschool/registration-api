package com.app.amrit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "USER_DTLS")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_FIRST_NAME")
    private String userFname;

    @Column(name = "USER_LAST_NAME")
    private String userLname;

    @Column(name = "USER_EMAIL", unique = true)
    private String userEmail;

    @Column(name = "USER_PHNO")
    private Integer userPhno;

    @Column(name = "USER_DOB")
    private LocalDate userDob;

    @Column(name = "USER_GENDER")
    private String userGender;

    @Column(name = "USER_COUNTRY")
    private Integer userCountry;

    @Column(name = "USER_STATE")
    private Integer userState;

    @Column(name = "USER_CITY")
    private Integer userCity;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_ACC_STATUS")
    private String userAccStatus;

    @Column(name = "CREATED_DATE")
    @CreationTimestamp
    private LocalDate createdDate;

    @Column(name = "UPDATED_DATE")
    @UpdateTimestamp
    private LocalDate updatedDate;
}