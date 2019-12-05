package br.com.stand.artilharia.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.stand.artilharia.model.Ambiente;

@Repository
public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {

    @Query("SELECT a FROM Ambiente a WHERE ?1 = NULL OR (a.descricao  LIKE %?1%)")
    Page<Ambiente> getAll(String busca, PageRequest pageRequest);

}