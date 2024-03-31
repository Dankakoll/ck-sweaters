package com.sweaters.demo1.repository;

import com.sweaters.demo1.domain.Sellers;
import com.sweaters.demo1.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellersRepository extends JpaRepository<Sellers, Long> {

}

