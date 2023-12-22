package org.generation.italy.jdbc_banca.model.entity;

public class Movimento {

		private Integer id;
		private Float importo;
		private String tipoOperazione;
		private String iban;
		
		
		public Movimento(Integer id, Float importo, String tipoOperazione, String iban) {

			this.id = id;
			this.importo = importo;
			this.tipoOperazione = tipoOperazione;
			this.iban = iban;
		}
		
		
}
