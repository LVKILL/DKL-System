import java.util.Collection;

public class Reserva {

	private Integer idReserva;

	private Cliente cliente;

	private LocalDateTime data;

	private Boolean ativo;

	private BigDecimal valorTotal;

	private Collection<Arma> arma;

	private Collection<Ambiente> ambiente;

	private ArmaLocada armaLocada;

	private Cliente cliente;

}
