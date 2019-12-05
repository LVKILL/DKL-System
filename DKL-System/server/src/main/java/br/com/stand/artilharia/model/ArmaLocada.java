package br.com.stand.artilharia.model;

import br.com.stand.artilharia.dto.ArmaLocadaDto;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ArmaLocada implements Serializable {
  private static final long serialVersionUID = -1480985512327327668L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private int quantidade;
  @ManyToOne
  private Reserva reserva;
  @ManyToOne
  private Arma arma;

  public static ArmaLocadaDto converterParaDto(ArmaLocada armaLocada) {
    return ArmaLocadaDto.builder().id(armaLocada.arma.getId()).quantidade(armaLocada.quantidade).build();
  }
}