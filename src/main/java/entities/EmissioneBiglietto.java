package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmissioneBiglietto {
	@Id
	@GeneratedValue
	private UUID idEmissione;
	private LocalDate dataEmissione;

	@ManyToOne
	@JoinColumn(name = "utenti")
	private Utente utente;

	@ManyToOne
	@JoinColumn(name = "IdPuntoVendita")
	private PuntiVendita idPuntoVendita;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idBiglietto", referencedColumnName = "idBiglietto")
	private VidimazioneBiglietti vidimazione;

	@ManyToOne
	@JoinColumn(name = "bigliettoEmesso")
	private DistributoriAutomatici distributoreBi;

	public EmissioneBiglietto emettiBiglietto(Utente utente) {
		EmissioneBiglietto biglietto = new EmissioneBiglietto();

		biglietto.setIdEmissione(UUID.randomUUID());
		biglietto.setIdPuntoVendita(this.idPuntoVendita);
		biglietto.setDataEmissione(LocalDate.now());

		return biglietto;
	}

	@Override
	public String toString() {
		return "EmissioneBiglietto [idEmissione=" + idEmissione + ", dataEmissione=" + dataEmissione + ", idBiglietto="
				+ ", idPuntoVendita=" + idPuntoVendita + ", utente=" + utente + ", getIdEmissione()=" + getIdEmissione()
				+ ", getDataEmissione()=" + getDataEmissione() + ", getIdBiglietto()=" + ", getIdPuntoVendita()="
				+ getIdPuntoVendita() + ", getUtente()=" + getUtente() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public EmissioneBiglietto(LocalDate dataEmissione, Utente utente, VidimazioneBiglietti vidimazione,
			DistributoriAutomatici distributoreBi) {
		super();
		this.dataEmissione = dataEmissione;
		this.utente = utente;
		this.vidimazione = vidimazione;
		this.distributoreBi = distributoreBi;
	}

}
