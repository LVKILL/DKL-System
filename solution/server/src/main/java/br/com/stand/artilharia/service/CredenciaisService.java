package br.com.stand.artilharia.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.stand.artilharia.exception.AlreadyRegistredException;
import br.com.stand.artilharia.model.Credenciais;
import br.com.stand.artilharia.repository.CredenciaisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class CredenciaisService {

  private CredenciaisRepository repository;
  private PasswordEncoder passwordEncoder;

  public Credenciais register(Credenciais credenciais) {
    credenciais.setPassword(passwordEncoder.encode(credenciais.getPassword()));
    try {
      return repository.save(credenciais);
    } catch (DataIntegrityViolationException e) {
      log.info(e.getCause());
      throw new AlreadyRegistredException("Email já cadastrado", "Se esta conta te pertencer, tente recuperar",
          "error.credential.email.arlreadyRegistred");
    }
  }
}
