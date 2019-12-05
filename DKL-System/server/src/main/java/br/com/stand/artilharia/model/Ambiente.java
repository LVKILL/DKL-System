package br.com.stand.artilharia.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ambiente implements Modelo, Serializable {
    private static final long serialVersionUID = -3231050480459880668L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Precisa preencher a descrição")
    private String descricao;
    @NotNull(message = "Precisa preencher a área")
    private Integer area;
    @NotNull(message = "Precisa preencher alvo")
    private Boolean alvo;

    public String getResumo() {
        return descricao + " - área: " + area + " - alvo: " + (alvo ? "SIM" : "NÃO");
    }
}