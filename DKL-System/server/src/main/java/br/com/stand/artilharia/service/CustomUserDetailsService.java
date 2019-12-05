package br.com.stand.artilharia.service;

import br.com.stand.artilharia.repository.CredenciaisRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private CredenciaisRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) {
    return repository.findCredentialByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado:" + username));
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> new UsernameNotFoundException("Usuário com id: : " + id + " não encontrado"));
  }
}
