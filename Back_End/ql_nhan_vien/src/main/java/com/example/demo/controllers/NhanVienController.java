package com.example.demo.controllers;

import com.example.demo.service.QlNhanVienServices;
import com.example.demo.utility.Utility;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired; //Cho phép Dependency Injection
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus; // Trả về status
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*; // Xử lý các request
import jakarta.validation.Valid;// Kiểm tra dữ liệu hợp lệ

import java.io.IOException;
import java.util.List;// Trả về danh sách

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;

import com.example.demo.DTOs.*;
import com.example.demo.mapper.*;

@RestController // class này xử lý yêu cầu HTTP và trả về Json
@RequestMapping("/employee")// Địa chỉ url gốc
@CrossOrigin(origins = "http://localhost:4200")

public class NhanVienController {

    @Autowired
    private QlNhanVienServices qlNhanVienServices;

    /*@GetMapping("/roles")
    public ResponseEntity<List<String>> getUserRoles(@RequestParam Long userId) {
        List<String> roles = qlNhanVienServices.getUserRoles(userId);
        return ResponseEntity.ok(roles);
    }*/
    // Dành cho user thường
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<List<NhanVienDTO>> getAllForUser() {
        return qlNhanVienServices.getAllForUser();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<NhanVienDTO> getEmployeeByIdForUser(@PathVariable Long id) {
        return qlNhanVienServices.getEmployeeByIdForUser(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/search")
    public ResponseEntity<List<NhanVienDTO>> searchEmployeesForUser(@RequestParam String name) {
        return qlNhanVienServices.searchEmployeesForUser(name);
    }

    // Dành cho admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<QuanLyDTO>> getAllForAdmin() {
        return qlNhanVienServices.getAllForAdmin();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<QuanLyDTO> getEmployeeByIdForAdmin(@PathVariable Long id) {
        return qlNhanVienServices.getEmployeeByIdForAdmin(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/search")
    public ResponseEntity<List<QuanLyDTO>> searchEmployeesForAdmin(@RequestParam String name) {
        return qlNhanVienServices.searchEmployeesForAdmin(name);
    }









    /*@GetMapping("/admin/export")
    public void exportData(HttpServletResponse response) {

        try { List<String[]> data = qlNhanVienServices.getDataForExport();
            String[] headers = {"Header1", "Header2", "Header3"};
            Workbook workbook = Utility.exportToExcel(data, headers);

            // Set response headers
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=export.xlsx");

            // Write workbook to response output stream
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            Utility.handleException(e); }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateData(@Valid @RequestBody QuanLyDTO quanLyDTO, BindingResult result) {
        String errors = Utility.validate(result);
        if (errors != null) {
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok("Validation successful");
    @PostMapping // Thêm dữ liệu
    public ResponseEntity<QuanLyDTO> addNv(@Valid @RequestBody NhanVien nv) {
        return qlNhanVienServices.addNv(nv);
    } // trả về trạng thái created(201) và nhân viên vừa tạo

    @PutMapping("/{id}") // Chỉnh sửa dữ liệu theo id
    public ResponseEntity<QuanLyDTO> updateNv(@PathVariable Long id, @Valid @RequestBody NhanVien nhanVienDetails) {
        // @PathVariable để gán id vào {id}
        // @RequestBody để gắn dữ liệu gửi đi vào nhanVienDetails
        return qlNhanVienServices.updateNv(id,nhanVienDetails);
    }

    @DeleteMapping("/{id}")// Xóa nhân viên theo id
    public ResponseEntity<Void> deleteNv(@PathVariable Long id) {
        return qlNhanVienServices.deleteNv(id);
    }
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
    }*/
}
