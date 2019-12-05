package br.com.stand.artilharia.repository;

import br.com.stand.artilharia.model.ArmaLocada;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArmaLocadaRepository extends JpaRepository<ArmaLocada, Long> {

    @Query("SELECT al FROM ArmaLocada al WHERE al.reserva.id= ?1")
    Set<ArmaLocada> buscarArmasPorReserva(Long reservaId);
}
