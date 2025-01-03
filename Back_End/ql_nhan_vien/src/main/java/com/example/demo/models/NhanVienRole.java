package com.example.demo.models;

// Import các chú thích đánh giấu class này thuộc JPA
import jakarta.persistence.Entity; //Class thuộc JPA
import jakarta.persistence.Id;// Trường này là Primary Key
import jakarta.persistence.GeneratedValue; // Tự động gen giá trị độc nhất cho PK
import jakarta.persistence.GenerationType; // Định nghĩa phương thức gen giá trị

@Entity
public class NhanVienRole {
    @Id // Trường sau đây là PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Tự động gen 1 giá trị độc nhất cho mỗi đối tượng

    private Long id;

    private String title;

    public NhanVienRole(){};

    public NhanVienRole(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    } // Để lấy dữ liệu
    public void setId(Long id) {
        this.id = id;
    } // Để thêm/sửa dữ liệu
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
