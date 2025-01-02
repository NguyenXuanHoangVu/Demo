// Địa chỉ đường dẫn của package
package com.example.demo.models;
// Import các chú thích đánh giấu class này thuộc JPA
import jakarta.persistence.Entity; //Class thuộc JPA
import jakarta.persistence.Id;// Trường này là Primary Key
import jakarta.persistence.GeneratedValue; // Tự động gen giá trị độc nhất cho PK
import jakarta.persistence.GenerationType; // Định nghĩa phương thức gen giá trị

import jakarta.validation.constraints.Email;  //validate mail
import jakarta.validation.constraints.NotBlank; // không bỏ trống
import jakarta.validation.constraints.Size; // kích thước 

@Entity// Đánh dấu class thuộc JPA
public class NhanVien {

	@Id // Trường sau đây là PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Tự động gen 1 giá trị độc nhất cho mỗi đối tượng
	
	private Long id;
	
	@NotBlank(message = "Không được bỏ trống tên nhân viên") // Not null
	@Size(max = 100, message = "Tên nhân viên không được dài hơn 100 ký tự") // giới hạn 100 ký tự
	private String name;
	
	@Email(message = "Địa chỉ email không hợp lệ") // validate địa chỉ email
	private String email;
	
	public NhanVien() {}// Constructor 
	
	public NhanVien(String name, String email) {
		this.name = name;
		this.email = email;
	} // Constructor có tham số
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
