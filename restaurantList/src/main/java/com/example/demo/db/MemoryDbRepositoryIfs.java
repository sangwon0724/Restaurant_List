package com.example.demo.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {
	Optional<T> findById(int index); //Optional하게 값을 리턴
    T save(T entity); //저장
    void deleteById(int index); //삭제
    List<T> findAll(); //전체 리턴
}
