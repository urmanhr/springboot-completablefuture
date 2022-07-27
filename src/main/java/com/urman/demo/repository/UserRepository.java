package com.urman.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urman.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
