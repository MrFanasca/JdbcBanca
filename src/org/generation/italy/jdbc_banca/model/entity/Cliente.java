package org.generation.italy.jdbc_banca.model.entity;

public class Cliente {
	
	private String nominativo;
	private String codice_fiscale;
	private String indirizzo;
	
	
	public Cliente(String nominativo, String codice_fiscale, String indirizzo) {
		super();
		this.nominativo = nominativo;
		this.codice_fiscale = codice_fiscale;
		this.indirizzo = indirizzo;
	}
	
	
	
}
