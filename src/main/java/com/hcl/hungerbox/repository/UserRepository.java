package com.hcl.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.hungerbox.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
