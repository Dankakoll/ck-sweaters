package com.sweaters.demo1;

import org.springframework.data.jpa.repository.JpaRepository;

interface ItemsRepository extends JpaRepository<Items,Long> {
}
