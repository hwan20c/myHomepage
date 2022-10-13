package com.bh.tb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails{

  private static final String ROLE_PREFIX = "ROLE_";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int Id;
  @Column(name = "login_id")
  private String loginId;
  @Column
  private String password;
  @Column
  private boolean enable;

  @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable( 
	        name = "users_roles", 
	        joinColumns = @JoinColumn(
	          name = "users_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "roles_id", referencedColumnName = "id")) 
	private Set<Role> roles;

  @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(roles != null)
			roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole())));
		return list;
	}
	@Override
	public String getUsername() {
		return this.getLoginId();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return this.enable;
	}
          
}
