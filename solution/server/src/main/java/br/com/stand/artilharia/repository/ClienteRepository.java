package br.com.stand.artilharia.repository;

import br.com.stand.artilharia.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE ?1 = NULL OR (c.primeiroNome  LIKE %?1%)")
    Page<Cliente> getAll(String busca, PageRequest pageRequest);
}
