package org.generation.italy.jdbc_banca.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

// Classe entity-bean Conto che effettua il mapping del record della tabella Conto

public class Conto {
	
	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/	
		private String iban;
		private String valuta;
		private String codiceFiscale;
		private Float scoperto;
		private LocalDateTime dataOraIntestazione;

	/***********************/
	// COSTRUTTORE
	/***********************/	
	// costruttore usato per il metodo di INSERT
		public Conto(String iban, String codiceFiscale, String valuta, Float scoperto) {
			super();
			this.iban = iban;
			this.valuta = valuta;
			this.scoperto = scoperto;
			this.codiceFiscale = codiceFiscale;
			this.dataOraIntestazione = null;
		}
		
	// costruttore usato per i metodi di SELECT		
		public Conto(String iban, String valuta, String codiceFiscale, Float scoperto, LocalDateTime dataOraIntestazione) {
		
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
		public void setValuta(String valuta) {
			this.valuta = valuta;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public Float getScoperto() {
			return scoperto;
		}
		public void setScoperto(Float scoperto) {
			this.scoperto = scoperto;
		}

		public LocalDateTime getDataOraIntestazione() {
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
