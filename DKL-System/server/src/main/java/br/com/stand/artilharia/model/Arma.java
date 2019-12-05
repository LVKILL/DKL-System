package br.com.stand.artilharia.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.stand.artilharia.enums.Marca;
import br.com.stand.artilharia.enums.Calibre;
import br.com.stand.artilharia.enums.SituacaoDaArma;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Arma implements Serializable, Modelo {

    private static final long serialVersionUID = -4560616246394961016L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Precisa preencher a descrição")
    private String descricao;

    @NotNull(message = "Precisa preencher a marca")
    @Enumerated(EnumType.STRING)
    private Marca marca;

    @NotNull(message = "Precisa preencher o calibre")
    @Enumerated(EnumType.STRING)
    private Calibre calibre;

    @NotNull(message = "Precisa preencher a situação")
    @Enumerated(EnumType.STRING)
    private SituacaoDaArma situacao;

    public String getResumo(){
        return descricao + " - " + marca + " - " + calibre + " | " + situacao;
    }
}