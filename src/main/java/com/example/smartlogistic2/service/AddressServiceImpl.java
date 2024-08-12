package com.example.smartlogistic2.service;

import com.example.smartlogistic2.dao.AddressDao;
import com.example.smartlogistic2.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService
{
    @Autowired
    private AddressDao addressDao;


    @Override
    public Address addAddress(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address getAddressById(int id) {

        Optional<Address> address = addressDao.findById(id);
        if(address.isPresent())
        {
            return address.get();
        }
        else {
            return null;
        }

    }
}
