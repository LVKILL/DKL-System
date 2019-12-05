package br.com.stand.artilharia.repository;

import br.com.stand.artilharia.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

  @Query(value = "SELECT r FROM Reserva r " + "JOIN r.cliente c " + "WHERE ?1 = NULL")
  Page<Reserva> buscarTodos(String busca, PageRequest pageRequest);

  @Modifying
  @Query("UPDATE Reserva  SET ativa = false WHERE id =?1 ")
  Reserva inativar(Long id);
}