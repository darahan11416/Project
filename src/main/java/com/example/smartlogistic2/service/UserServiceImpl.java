package com.example.smartlogistic2.service;

import com.example.smartlogistic2.dao.UserDao;
import com.example.smartlogistic2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;


    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }



    @Override
    public User updateUser(User user) {
        return userDao.save(user);

    }

    @Override
    public User getUserByEmailAndStatus(String emailId, String status) {
        return userDao.findbyEmailIdAndStatus(emailId, status);
    }

    @Override
    public User getUserByEmailid(String emailId) {
        return userDao.findbyEmailId(emailId);
    }

    @Override
    public List<User> getUserByRole(String role) {
        return userDao.findByRole(role);
    }

    @Override
    public User getUserById(int userId) {

        Optional<User> optionalUser = userDao.findById(userId);
        if(optionalUser.isPresent())
        {
            return optionalUser.get();
        }else {
            return null;
        }



        //return null;
    }

    @Override
    public List<User> getUserByCourierAndRoleAndStatusIn(User courier, String role, List<String> status) {
        return userDao.findByCourierAndRoleAndStatusIn(courier, role, status);
    }

    @Override
    public User getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
        return userDao.findByEmailIdAndRoleAndStatus(emailId, role, status);
    }

    @Override
    public List<User> updateAllUser(List<User> users) {
        return userDao.saveAll(users);
    }

    @Override
    public List<User> getUserByRoleAndStatus(String role, String status) {
        return userDao.findByRoleAndStatus(role, status);
    }

    @Override
    public User getUserByCustomerIdAndStatus(String customerRefId, String status) {
        return userDao.findByCustomerRefIdAndStatus(customerRefId, status);
    }
}
