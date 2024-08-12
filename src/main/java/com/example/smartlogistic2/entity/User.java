package com.example.smartlogistic2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String customerRefId;

    private String courierRefId;

    private  String  firstName;

    private  String  lastName;

    private String emailId;

    private String password;

    private String  phoneNo;

    private String role;

    @OneToOne
    @JoinColumn(name = "Address_id")
    private Address address;

@ManyToOne
@JoinColumn(name = "Courier_id")
private User courier;

private String status;




}
