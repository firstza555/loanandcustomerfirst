package com.digitalacademy.customer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 1 ,max =100,message = "Plase type ypur name size between 1-100" )
    private String first_name;
    @NotNull
    @Size(min = 1 ,max =100,message = "Plase type ypur name size between 1-100" )
    private String last_name;
    @Email(message = "Plase type valid email")
    private String email;
    private String phoneNo;
    private Integer age;


}
