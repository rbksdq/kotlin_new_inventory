package com.learning.springbootkotlinh2.repository

import com.learning.springbootkotlinh2.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo : JpaRepository<Product, Long> {

    @Query("SELECT m FROM Product as m")
    fun getAllProducts(): List<Product>
}