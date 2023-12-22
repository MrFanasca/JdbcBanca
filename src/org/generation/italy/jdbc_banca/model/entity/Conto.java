package org.generation.italy.jdbc_banca.model.entity;

public class Conto {
	
	private String iban;
	private String valuta;
	private String codiceFiscale;
	private Integer scoperto;
	
	public Conto(String iban, String valuta, String codiceFiscale, Integer scoperto) {
		
		this.iban = iban;
		this.valuta = valuta;
		this.codiceFiscale = codiceFiscale;
		this.scoperto = scoperto;
	}
	
	
}
