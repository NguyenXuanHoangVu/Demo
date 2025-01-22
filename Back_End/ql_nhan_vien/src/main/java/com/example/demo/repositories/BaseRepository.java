package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean // Ngăn Spring Data JPA tạo ra bản sao ảo của BaseRepo
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    // Định nghĩa 1 query SQL native để tìm kiếm theo ID
    @Query(value = "SELECT n FROM #{#entityName} n WHERE n.id = ?1", nativeQuery = true)
    T findByIdNative(ID id);
}

