// Địa chỉ đường dẫn của package
package com.example.demo.models;
// Import các chú thích đánh giấu class này thuộc JPA
import jakarta.persistence.Entity; //Class thuộc JPA
import jakarta.persistence.Id;// Trường này là Primary Key
import jakarta.persistence.GeneratedValue; // Tự động gen giá trị độc nhất cho PK
import jakarta.persistence.GenerationType; // Định nghĩa phương thức gen giá trị
import jakarta.persistence.JoinColumn; // Định nghĩa FK
import  jakarta.persistence.ManyToOne; // Định nghĩa quan hệ nhiều-một
import jakarta.validation.constraints.Email;  //validate mail
import jakarta.validation.constraints.NotBlank; // không bỏ trống
import jakarta.validation.constraints.Size; // kích thước
import java.util.Date;

@Entity// Đánh dấu class thuộc JPA
public class NhanVien {

	@Id // Trường sau đây là PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Tự động gen 1 giá trị độc nhất cho mỗi đối tượng
	
	private Long id;
	
	@NotBlank(message = "Không được bỏ trống tên nhân viên") // Not null
	@Size(max = 100, message = "Tên nhân viên không được dài hơn 100 ký tự") // giới hạn 100 ký tự
	private String name;

	@Size(min=10, max=15, message = "Chỉ nhập số điện thoại trong khoảng 10-15 ký tự")
	private String phoneNumber;

	private Date birthday;
	
	@Email(message = "Địa chỉ email không hợp lệ") // validate địa chỉ email
	private String email;
	private String position;

	private	String username;
	private String password;

	@ManyToOne //qh nhiều nhân viên với 1 role
	@JoinColumn(name = "role_id") // cột role_id là FK
	private NhanVienRole role;
	
	public NhanVien() {}// Constructor 
	
	public NhanVien(String name, String phoneNumber, Date birthday, String email, String position, String username, String password, NhanVienRole role) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
		this.position = position;
		this.username = username;
		this.password = password;
		this.role = role;
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
	public NhanVienRole getRole() {return role;}
	public  void setRole(NhanVienRole role) {this.role = role;}
}
