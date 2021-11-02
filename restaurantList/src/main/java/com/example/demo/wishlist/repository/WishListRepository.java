package com.example.demo.wishlist.repository;

import com.example.demo.db.MemoryDbRepositoryAbstract;
import com.example.demo.wishlist.entity.WishListEntity;

import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}