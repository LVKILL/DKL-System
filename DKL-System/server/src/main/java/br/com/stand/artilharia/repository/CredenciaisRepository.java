package br.com.stand.artilharia.repository;

import br.com.stand.artilharia.model.Credenciais;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenciaisRepository extends JpaRepository<Credenciais, Long> {

  Optional<Credenciais> findCredentialByEmail(String email);

  @Override
  <S extends Credenciais> S save(S s);
}
