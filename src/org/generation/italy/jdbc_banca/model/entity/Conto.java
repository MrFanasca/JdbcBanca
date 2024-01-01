package org.generation.italy.jdbc_banca.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Conto {
	
	
	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/	
		private String iban;
		private String valuta;
		private String codiceFiscale;
		private Integer scoperto;
		private LocalDate dataOraIntestazione;

	/***********************/
	// COSTRUTTORE
	/***********************/	
		public Conto(String iban, String valuta, String codiceFiscale, Integer scoperto, LocalDate dataOraIntestazione) {
		
			this.iban = iban;
			this.valuta = valuta;
			this.codiceFiscale = codiceFiscale;
			this.scoperto = scoperto;
			this.dataOraIntestazione = dataOraIntestazione;
		}

	/********************/
	// GETTERS & SETTERS
	/********************/
		public String getIban() {
			return iban;
		}

		public String getValuta() {
			return valuta;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public Integer getScoperto() {
			return scoperto;
		}

		public LocalDate getDataOraIntestazione() {
			return dataOraIntestazione;
		}

    /***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/ 	
		@Override
		public int hashCode() {
			return Objects.hash(codiceFiscale, dataOraIntestazione, iban, scoperto, valuta);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Conto other = (Conto) obj;
			return Objects.equals(codiceFiscale, other.codiceFiscale)
					&& Objects.equals(dataOraIntestazione, other.dataOraIntestazione) && Objects.equals(iban, other.iban)
					&& Objects.equals(scoperto, other.scoperto) && Objects.equals(valuta, other.valuta);
		}
	
 
	
	
	
	
	
	
	
	
	
	
}
