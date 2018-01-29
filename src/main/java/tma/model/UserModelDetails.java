package tma.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Document(collection="account") 	 	
public class UserModelDetails implements UserDetails {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String username;
		private String password;
		private Set<String> roles = new HashSet<String>();
		private Set<GrantedAuthority> authorities = new HashSet<>();
	
		public UserModelDetails(String username, String password, Set<String> roles) {
			super();
			this.username = username;
			this.password = password;
			this.roles = roles;
		}

		public Set<String> getRoles() {
		return roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	public Collection<String> getRole() {
	return roles;
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
		return true;
	}
	
}




