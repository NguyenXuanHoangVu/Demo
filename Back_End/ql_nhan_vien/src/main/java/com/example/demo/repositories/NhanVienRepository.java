package com.example.demo.repositories;

import com.example.demo.models.NhanVien; //import class nhanVien
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; // import JPA repo
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// nhanVienRepo kế thừa các phương thức của JPA Repo
public interface NhanVienRepository extends BaseRepository<NhanVien, Long> {

    // Query để tìm theo tên
    @Query("SELECT nv FROM NhanVien nv WHERE LOWER(nv.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<NhanVien> findByNameContainingIgnoreCase(@Param("name") String name);

    // Query để tìm theo Username
    @Query("SELECT n FROM NhanVien n WHERE n.username = ?1")
    Optional<NhanVien> findByUsername(String username);

    // Query để tìm theo email
    @Query(value = "SELECT * FROM NhanVien WHERE email = ?1", nativeQuery = true)
    Optional<NhanVien> findByEmailNative(String email);

    /*@Query("SELECT r.title FROM NhanVienRole r JOIN NhanVien n ON n.role = r.id WHERE n.id = :userId")
    List<String> findRolesByUserId(@Param("userId") Long userId);*/
}
