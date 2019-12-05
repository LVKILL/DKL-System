package br.com.stand.artilharia.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente implements Serializable, Modelo {
  private static final long serialVersionUID = -7838886369520351317L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotEmpty(message = "Primeiro nome  não pode ser vazio")
  private String primeiroNome;

  @NotEmpty(message = "Sobrenome  não pode ser vazio")
  private String ultimoNome;

  @NotNull(message = "RG  não pode ser vazio")
  private Long rg;

  @NotNull(message = "CPF  não pode ser vazio")
  private Long cpf;

  @NotNull(message = "Telefone não pode ser vazio")
  private Long telefone;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @NotNull(message = "Data de nascimento não pode ser vazia")
  private LocalDate dataNascimento;

  public String getResumo() {
    return String.format("%s %s - %s", primeiroNome, ultimoNome, cpf);
  }
}
