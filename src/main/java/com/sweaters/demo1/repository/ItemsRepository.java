package com.sweaters.demo1.repository;

import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemsRepository extends JpaRepository<Items,Long> {

    @Query("select i from Items i where i.origin=?1 and i.origin_id=?2")
    Items findBySecondKey (String Origin, Long Origin_id);
}

