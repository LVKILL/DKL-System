package br.com.stand.artilharia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Reserva implements Serializable {

  private static final long serialVersionUID = -5854016886822004154L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  private Cliente cliente;

  @ManyToOne
  private Ambiente ambiente;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime inicioDaLocacao;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
  private LocalDateTime fimDaLocacao;
  @OneToMany(mappedBy = "reserva",fetch = FetchType.EAGER)
  private Set<ArmaLocada> armaLocadas;
  @Column(columnDefinition = "boolean default true")
  private Boolean ativa;
}