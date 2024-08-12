package com.example.smartlogistic2.service;

import com.example.smartlogistic2.entity.Address;

public interface AddressService
{
    Address addAddress(Address address);

    Address updateAddress(Address address);

    Address getAddressById(int id) ;


}
