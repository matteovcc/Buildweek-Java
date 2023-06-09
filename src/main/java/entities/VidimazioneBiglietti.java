package entities;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "biglietti_vidimati")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class VidimazioneBiglietti {
	@Id
	@GeneratedValue
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "idBiglietto")
	private EmissioneBiglietto bigliettoVidimato;

	@ManyToOne
	@JoinColumn(name = "idMezzo")
	private Mezzo mezzo; // Mezzo sulla quale è stato vidimato

	private LocalDate dataVidimazione;

	public VidimazioneBiglietti(EmissioneBiglietto biglietto, Mezzo mezzo, LocalDate dataVidimazione) {
		super();
		this.bigliettoVidimato = biglietto;
		this.mezzo = mezzo;
		this.dataVidimazione = dataVidimazione;
	}

}