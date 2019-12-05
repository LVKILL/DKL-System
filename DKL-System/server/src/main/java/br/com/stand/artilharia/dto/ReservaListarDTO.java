package br.com.stand.artilharia.dto;

import br.com.stand.artilharia.model.ArmaLocada;
import br.com.stand.artilharia.model.Reserva;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaListarDTO {

  private Long id;
  private String cliente;
  private String ambiente;
  private LocalDateTime inicio;
  private LocalDateTime fim;
  private Integer qtdArmas;
  private Boolean ativa;

  public static ReservaListarDTO converter(Reserva reserva) {
    return ReservaListarDTO.builder().id(reserva.getId()).cliente(reserva.getCliente().getResumo())
        .ambiente(reserva.getAmbiente().getDescricao()).inicio(reserva.getInicioDaLocacao()).ativa(reserva.getAtiva())
        .fim(reserva.getFimDaLocacao()).qtdArmas(totalArmas(reserva.getArmaLocadas())).build();
  }

  private static Integer totalArmas(Set<ArmaLocada> armaLocadas) {
    return armaLocadas.stream().map(a -> a.getQuantidade()).collect(Collectors.summingInt(Integer::intValue));
  }

}
