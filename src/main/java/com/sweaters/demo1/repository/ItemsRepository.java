package com.sweaters.demo1.repository;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items,Long> {
}

