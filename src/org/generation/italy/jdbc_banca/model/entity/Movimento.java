package org.generation.italy.jdbc_banca.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Movimento {

	
	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/	
		private Integer id;
		private Float importo;
		private String tipoOperazione;
		private String iban;
		private LocalDate dataOraOperazione;

	/***********************/
	// COSTRUTTORE
	/***********************/
	// costruttore usato per il metodo di INSERT		
		public Movimento(Integer id, Float importo, String tipoOperazione, String iban) {

			this.id = id;
			this.importo = importo;
			this.tipoOperazione = tipoOperazione;
			this.iban = iban;
			this.dataOraOperazione = LocalDate.now();
		}
		
	// costruttore usato per i metodi di SELECT		
		public Movimento(Integer id, Float importo, String tipoOperazione, String iban, LocalDate dataOraOperazione) {
			
			this.id = id;
			this.importo = importo;
			this.tipoOperazione = tipoOperazione;
			this.iban = iban;
			this.dataOraOperazione = dataOraOperazione;
		}	
		
	/********************/
	// GETTERS & SETTERS
	/********************/
		public Integer getId() {
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

		public LocalDate getDataOraOperazione() {
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
