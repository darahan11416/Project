package com.example.smartlogistic2.dao;

import com.example.smartlogistic2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
    User findbyEmailId(String email);

    User findbyEmailIdAndStatus(String email, String status);

    User findByRoleAndStatusIn(String role, List<String> status);

    List<User> findByRole(String role);

    List<User> findByCourierAndRole(User courier, String role);

    List<User> findByCourierAndRoleAndStatusIn(User courier, String role, List<String> status);

    User findByEmailIdAndRoleAndStatus(String emailId, String role, String status);

    List<User> findByRoleAndStatus(String role, String status);

    User findByCustomerRefIdAndStatus(String customerRefId, String status);






}
