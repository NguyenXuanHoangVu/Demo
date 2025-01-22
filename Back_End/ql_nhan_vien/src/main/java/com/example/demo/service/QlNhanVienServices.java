package com.example.demo.service;

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.utility.Utility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.DTOs.*;
import com.example.demo.mapper.*;

@Service
public class QlNhanVienServices {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    /*public List<String> getUserRoles(Long userId) {
        return nhanVienRepository.findRolesByUserId(userId);
    }*/

    // Thao tac voi du lieu bang repo roi mapping sang DTO tuong ung voi role cua nguoi dung
    // Danh cho user thuong

    public ResponseEntity<List<NhanVienDTO>> getAllForUser() {
        List<NhanVien> employees = nhanVienRepository.findAll();
        List<NhanVienDTO> dtos = employees.stream().map(Mapping::toNhanVienDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos); }

    public ResponseEntity<NhanVienDTO> getEmployeeByIdForUser(@PathVariable Long id) {
        NhanVien nv = nhanVienRepository.findById(id) .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã " + id ));
        return ResponseEntity.ok(Mapping.toNhanVienDTO(nv));
    }

    public ResponseEntity<List<NhanVienDTO>> searchEmployeesForUser(@RequestParam String name) {
        List<NhanVien> employees = nhanVienRepository.findByNameContainingIgnoreCase(name);
        List<NhanVienDTO> dtos = employees.stream().map(Mapping::toNhanVienDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Dành cho admin

    public ResponseEntity<List<QuanLyDTO>> getAllForAdmin() {
        List<NhanVien> employees = nhanVienRepository.findAll();
        List<QuanLyDTO> dtos = employees.stream().map(Mapping::toQuanLyDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<QuanLyDTO> getEmployeeByIdForAdmin(@PathVariable Long id) {
        NhanVien nv = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã " + id ));
        return ResponseEntity.ok(Mapping.toQuanLyDTO(nv));
    }

    public ResponseEntity<List<QuanLyDTO>> searchEmployeesForAdmin(@RequestParam String name) {
        List<NhanVien> employees = nhanVienRepository.findByNameContainingIgnoreCase(name);
        List<QuanLyDTO> dtos = employees.stream().map(Mapping::toQuanLyDTO).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<QuanLyDTO> addNv(@Valid @RequestBody NhanVien nv) {
        NhanVien nvm = nhanVienRepository.save(nv);
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapping.toQuanLyDTO(nvm));
    } // trả về trạng thái created(201) và nhân viên vừa tạo

    public ResponseEntity<QuanLyDTO> updateNv(@PathVariable Long id, @Valid @RequestBody NhanVien nhanVienDetails) {
        // @PathVariable để gán id vào {id}
        // @RequestBody để gắn dữ liệu gửi đi vào nhanVienDetails
        NhanVien nv = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã " + id ));
        // Tìm nhân viên theo mã id ban đầu. Nếu không có thì thông báo không tìm thấy
        nv.setName(nhanVienDetails.getName());
        nv.setBirthday(nhanVienDetails.getBirthday());
        nv.setEmail(nhanVienDetails.getEmail());
        nv.setPhoneNumber(nhanVienDetails.getPhoneNumber());
        nv.setPosition(nhanVienDetails.getPosition());
        nv.setUsername(nhanVienDetails.getUsername());
        nv.setPassword(nhanVienDetails.getPassword());
        // update thông tin của nhân viên đã tìm thấy bằng dữ liệu từ nhanVienDetails
        NhanVien unv = nhanVienRepository.save(nv);// Lưu thông tin của nhân viên vừa sửa vào database
        return ResponseEntity.ok(Mapping.toQuanLyDTO(unv));
        // Trả về trạng thái ok(200) và thông tin nhân viên đã cập nhập
    }

    public ResponseEntity<Void> deleteNv(@PathVariable Long id) {
        if(!nhanVienRepository.existsById(id)) { // Kiểm tra có nhân viên nào có id này không
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            // Nếu không có thì trả về trạng thái 404
        }
        nhanVienRepository.deleteById(id);// Xóa nhân viên có id đã nhập
        return ResponseEntity.noContent().build(); // Trả về trạng thái trống để biểu thị đã xóa
    }
    /*

        // Method to get data for export
        public List<String[]> getDataForExport() {
            // Fetch and prepare data
            return List.of(
                    new String[]{"Data1", "Data2", "Data3"},
                    new String[]{"Data4", "Data5", "Data6"}
            );
        }

        // Method to call an external API
        public String callExternalApi() {
            String url = "https://api.example.com/data";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer your-token");

            ResponseEntity<String> response = Utility.callApi(url, HttpMethod.GET, null, headers);
            return response.getBody();
        }

    /*
    public ResponseEntity<List<NhanVien>> getAll(){
        return ResponseEntity.ok(nhanVienRepository.findAll());
        //Trả về trạng thái ok (200) và danh sách nhân viên
    }

    public ResponseEntity<NhanVien> getEmployeeById(@PathVariable Long id) {
        NhanVien nv = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã " + id ));
        return ResponseEntity.ok(nv);
    }

    public ResponseEntity<List<NhanVien>> searchEmployees(@RequestParam String name)
    {
        List<NhanVien> employees = nhanVienRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<NhanVien> addNv(@Valid @RequestBody NhanVien nv) {
        NhanVien nvm = nhanVienRepository.save(nv);
        return ResponseEntity.status(HttpStatus.CREATED).body(nvm);
    } // trả về trạng thái created(201) và nhân viên vừa tạo

    public ResponseEntity<NhanVien> updateNv(@PathVariable Long id, @Valid @RequestBody NhanVien nhanVienDetails) {
        // @PathVariable để gán id vào {id}
        // @RequestBody để gắn dữ liệu gửi đi vào nhanVienDetails
        NhanVien nv = nhanVienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với mã " + id ));
        // Tìm nhân viên theo mã id ban đầu. Nếu không có thì thông báo không tìm thấy
        nv.setName(nhanVienDetails.getName());
        nv.setEmail(nhanVienDetails.getEmail());
        // update thông tin của nhân viên đã tìm thấy bằng dữ liệu từ nhanVienDetails
        NhanVien unv = nhanVienRepository.save(nv);// Lưu thông tin của nhân viên vừa sửa vào database
        return ResponseEntity.ok(unv);
        // Trả về trạng thái ok(200) và thông tin nhân viên đã cập nhập
    }

    public ResponseEntity<Void> deleteNv(@PathVariable Long id) {
        if(!nhanVienRepository.existsById(id)) { // Kiểm tra có nhân viên nào có id này không
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            // Nếu không có thì trả về trạng thái 404
        }
        nhanVienRepository.deleteById(id);// Xóa nhân viên có id đã nhập
        return ResponseEntity.noContent().build(); // Trả về trạng thái trống để biểu thị đã xóa
    }*/
}
