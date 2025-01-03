package com.example.demo.repositories;

import com.example.demo.models.NhanVien; //import class nhanVien
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository; // import JPA repo
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// nhanVienRepo kế thừa các phương thức của JPA Repo
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> { 
    @Query("SELECT nv FROM NhanVien nv WHERE LOWER(nv.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<NhanVien> findByNameContainingIgnoreCase(@Param("name") String name);
}
