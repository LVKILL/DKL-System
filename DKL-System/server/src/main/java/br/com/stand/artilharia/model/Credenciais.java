package br.com.stand.artilharia.model;

import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Credenciais implements UserDetails {

  private static final long serialVersionUID = -6888749860111966867L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long id;

  private String email;

  private String password;

  private Boolean enabled;

  @Override
  public List<GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
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
    return enabled;
  }

  @Override
  public String getUsername() {
    return getEmail();
  }
}
