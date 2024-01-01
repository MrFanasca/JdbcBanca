package org.generation.italy.jdbc_banca.model.entity;

import java.util.Objects;

public class Cliente {
	
	
	/***********************/
    // DEFINIZIONE ATTRIBUTI
    /***********************/	
		private String nominativo;
		private String codiceFiscale;
		private String indirizzo;
	
	/***********************/
	// COSTRUTTORE
	/***********************/	
		public Cliente(String nominativo, String codiceFiscale, String indirizzo) {
			
			this.nominativo = nominativo;
			this.codiceFiscale = codiceFiscale;
			this.indirizzo = indirizzo;
		}
	
	/********************/
	// GETTERS & SETTERS
	/********************/	
		public String getNominativo() {
			return nominativo;
		}

		public String getCodiceFiscale() {
			return codiceFiscale;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

    /***********************************************************************/
    // METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
    /***********************************************************************/  
		@Override
		public int hashCode() {
			return Objects.hash(codiceFiscale, indirizzo, nominativo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cliente other = (Cliente) obj;
			return Objects.equals(codiceFiscale, other.codiceFiscale) && Objects.equals(indirizzo, other.indirizzo)
					&& Objects.equals(nominativo, other.nominativo);
		}
	
	
	
	
	
	
	
	
}
