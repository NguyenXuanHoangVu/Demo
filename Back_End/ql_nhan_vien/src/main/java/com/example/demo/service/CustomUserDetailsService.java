package com.example.demo.service;

import com.example.demo.models.NhanVien;
import com.example.demo.repositories.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NhanVien nhanVien = nhanVienRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                nhanVien.getUsername(),
                nhanVien.getPassword(),
                getAuthorities(nhanVien)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(NhanVien nhanVien) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + nhanVien.getRole().getTitle()));
    }

}
