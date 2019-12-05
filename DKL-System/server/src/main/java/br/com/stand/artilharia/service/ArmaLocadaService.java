package br.com.stand.artilharia.service;

import br.com.stand.artilharia.dto.ArmaLocadaDto;
import br.com.stand.artilharia.model.Arma;
import br.com.stand.artilharia.model.ArmaLocada;
import br.com.stand.artilharia.model.Reserva;
import br.com.stand.artilharia.repository.ArmaLocadaRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArmaLocadaService {
  private ArmaService armaService;
  private ArmaLocadaRepository repo;

  public Set<ArmaLocada> salvaArmasLocadas(Set<ArmaLocadaDto> set, Reserva reserva) {
    Set<ArmaLocada> armasDaReserva = repo.buscarArmasPorReserva(reserva.getId());
    repo.deleteInBatch(armasDaReserva);
    Set<ArmaLocada> armasLocadas = new HashSet<>();
    set.forEach(dto -> {
      System.out.println(dto.getId());
      Arma arma = armaService.findOne(dto.getId());
      ArmaLocada armaLocada = ArmaLocada.builder().arma(arma).quantidade(dto.getQuantidade()).reserva(reserva).build();
      repo.save(armaLocada);
      armasLocadas.add(armaLocada);
    });
    return armasLocadas;
  }

}
