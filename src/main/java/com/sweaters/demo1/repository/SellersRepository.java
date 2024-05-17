package com.sweaters.demo1.repository;

import com.sweaters.demo1.api.SellerController;
import com.sweaters.demo1.domain.Items;
import com.sweaters.demo1.domain.Sellers;
import com.sweaters.demo1.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SellersRepository extends JpaRepository<Sellers, Long> {
    //Поиск по артикулу и по месту сбора данных

    @Query("select s from Sellers s where s.origin=?1 and s.origin_id=?2")
    Sellers findBySecondKey (String Origin, Long Origin_id);
    //Поиск по имени продавца, нужно будет еще добавить место сбора информации о нем.
    @Query("select s from Sellers s where s.seller_name like ?1")
    List<Sellers> findBySeller_name(String seller_name);
}

