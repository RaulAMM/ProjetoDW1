package Bus.model.entities;

import java.time.LocalDate;

public class User {

	private Long CPF;
	private Long RG;
	private String name;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	
	
	//getts and setters
	public String getName() {
		return name;
	}
	public Long getCPF() {
		return CPF;
	}
	public void setCPF(Long cPF) {
		CPF = cPF;
	}
	public Long getRG() {
		return RG;
	}
	public void setRG(Long rG) {
		RG = rG;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
}
