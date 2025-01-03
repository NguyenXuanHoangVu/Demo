package com.example.demo.controllers;

import com.example.demo.service.QlNhanVienServices;
import org.springframework.beans.factory.annotation.Autowired; //Cho phép Dependency Injection
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus; // Trả về status
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Xử lý các request
import jakarta.validation.Valid;// Kiểm tra dữ liệu hợp lệ

import java.util.List;// Trả về danh sách

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;

@RestController // class này xử lý yêu cầu HTTP và trả về Json
@RequestMapping("/employees")// Địa chỉ url gốc
@CrossOrigin(origins = "http://localhost:4200")

public class NhanVienController {
    @Autowired
    private NhanVienRepository nhanVienRepo;
    private QlNhanVienServices qlNhanVienServices;
    
    @GetMapping //Lấy dữ liệu
    public ResponseEntity<List<NhanVien>> getAllUser(){
        return qlNhanVienServices.getAll();
        //Trả về trạng thái ok (200) và danh sách nhân viên
    }
    
    @GetMapping("/{id}") // Lấy dữ liệu theo id
    public ResponseEntity<NhanVien> getEmployeeById(@PathVariable Long id) {
        return qlNhanVienServices.getEmployeeById(id);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<NhanVien>> searchEmployees(@RequestParam String name)
    { 
    	return qlNhanVienServices.searchEmployees(name);
    	}
    
    @PostMapping // Thêm dữ liệu
    public ResponseEntity<NhanVien> addNv(@Valid @RequestBody NhanVien nv) {
        return qlNhanVienServices.addNv(nv);
    } // trả về trạng thái created(201) và nhân viên vừa tạo
    
    @PutMapping("/{id}") // Chỉnh sửa dữ liệu theo id
    public ResponseEntity<NhanVien> updateNv(@PathVariable Long id, @Valid @RequestBody NhanVien nhanVienDetails) {
        // @PathVariable để gán id vào {id}
        // @RequestBody để gắn dữ liệu gửi đi vào nhanVienDetails
        return qlNhanVienServices.updateNv(id,nhanVienDetails);
    }
    
    @DeleteMapping("/{id}")// Xóa nhân viên theo id
    public ResponseEntity<Void> deleteNv(@PathVariable Long id) {
        return qlNhanVienServices.deleteNv(id);
    }
}
