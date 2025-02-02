package com.codeX.codex_bank.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codeX.codex_bank.dto.BankResponse.BankResponseBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String email;
     @Column(nullable = false)

    private String password;
    private String phoneNumber;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String status;

    private Role role;

    @CurrentTimestamp
    private String creationTime;
    @UpdateTimestamp
    private String updateTime;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
return List.of(new SimpleGrantedAuthority(role.name()));

    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
	public String getPassword() {
		return password;
	}

	
	@Override
	public boolean isEnabled() {
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
 

	
}
