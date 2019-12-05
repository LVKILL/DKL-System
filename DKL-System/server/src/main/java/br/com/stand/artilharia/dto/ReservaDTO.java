package br.com.stand.artilharia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
  private Long clienteSelecionado;

  private Long ambienteSelecionado;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime inicioDaLocacao;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime fimDaLocacao;

  private Set<ArmaLocadaDto> armasLocadas;

  private Boolean ativa;
}
