package com.example.demo.models.DTOs;

import com.example.demo.models.NhanVienRole;

import java.util.Date;
// Du lieu cho quan ly
public class QuanLyDTO {
        private Long id;
        private String name;
        private Date birthday;
        private String phoneNumber;
        private String email;
        private String position;
        private String username;
        private String password;

    public Long getId() {
        return id;
    } // Để lấy dữ liệu
    public void setId(Long id) {
        this.id = id;
    } // Để thêm/sửa dữ liệu
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
