package com.sweaters.demo1.repository;

import com.sweaters.demo1.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
