package org.generation.italy.jdbc_banca.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

// Classe entity-bean Movimento che effettua il mapping del record della tabella Movimento

public class Movimento {
	
	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/	
		private Long id;
		private Float importo;
		private String tipoOperazione;
		private String iban;
		private LocalDateTime dataOraOperazione;

	/***********************/
	// COSTRUTTORE
	/***********************/
	// costruttore usato per il metodo di INSERT		
		public Movimento(Long id, Float importo, String tipoOperazione, String iban) {

			this.id = id;
			this.importo = importo;
			this.tipoOperazione = tipoOperazione;
			this.iban = iban;
			this.dataOraOperazione = LocalDateTime.now();
		}
		
	// costruttore usato per i metodi di SELECT		
		public Movimento(Long id, Float importo, String tipoOperazione, String iban, LocalDateTime dataOraOperazione) {
			
			this.id = id;
			this.importo = importo;
			this.tipoOperazione = tipoOperazione;
			this.iban = iban;
			this.dataOraOperazione = dataOraOperazione;
		}	
		
	/********************/
	// GETTERS & SETTERS
	/********************/
		public Long getId() {
			return id;
		}

		public Float getImporto() {
			return importo;
		}

		public String getTipoOperazione() {
			return tipoOperazione;
		}

		public String getIban() {
			return iban;
		}

		public LocalDateTime getDataOraOperazione() {
			return dataOraOperazione;
		}

	/***********************************************************************/
	// METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
	/***********************************************************************/ 		
		@Override
		public int hashCode() {
			return Objects.hash(dataOraOperazione, iban, id, importo, tipoOperazione);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Movimento other = (Movimento) obj;
			return Objects.equals(dataOraOperazione, other.dataOraOperazione) && Objects.equals(iban, other.iban)
					&& Objects.equals(id, other.id) && Objects.equals(importo, other.importo)
					&& Objects.equals(tipoOperazione, other.tipoOperazione);
		}

		
		
		
		
		
		
		
		
}
