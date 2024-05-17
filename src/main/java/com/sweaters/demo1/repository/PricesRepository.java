package com.sweaters.demo1.repository;

import com.sweaters.demo1.domain.Prices;
import com.sweaters.demo1.domain.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PricesRepository extends JpaRepository<Prices,Long> {
    //Поиск по артикулу и по месту сбора данных
    @Query("select p from Prices p where p.origin=?1 and p.origin_id=?2")
    List<Prices> findBySecondKey (String Origin, Long Origin_id);
}
