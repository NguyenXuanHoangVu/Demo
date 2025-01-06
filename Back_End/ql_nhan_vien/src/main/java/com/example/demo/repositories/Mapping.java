package com.example.demo.repositories;

import com.example.demo.models.DTOs.NhanVienDTO;
import com.example.demo.models.DTOs.QuanLyDTO;
import com.example.demo.models.NhanVien;
import org.apache.catalina.User;

public class Mapping {

    public static QuanLyDTO toQuanLyDTO(NhanVien nv){
        QuanLyDTO quanLyDTO = new QuanLyDTO();
        quanLyDTO.setId(nv.getId());
        quanLyDTO.setName(nv.getName());
        quanLyDTO.setBirthday(nv.getBirthday());
        quanLyDTO.setPhoneNumber(nv.getPhoneNumber());
        quanLyDTO.setEmail(nv.getEmail());
        quanLyDTO.setPosition(nv.getPosition());
        quanLyDTO.setUsername(nv.getUsername());
        quanLyDTO.setPassword(nv.getPassword());
        return quanLyDTO;
    }
    public static NhanVienDTO toNhanVienDTO(NhanVien nv){
        NhanVienDTO nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.setName(nv.getName());
        nhanVienDTO.setBirthday(nv.getBirthday());
        nhanVienDTO.setEmail(nv.getEmail());
        nhanVienDTO.setPosition(nv.getPosition());
        return nhanVienDTO;
    }
}
