package org.generation.italy.jdbc_banca.model.dao;

// Classe che raccoglie tutte le query utilizzate nei vari metodi DAO. Si centralizza il catalogo di modo da avere un 
// controllo globale su possibili errori sintattici e/o di uso delle query SQL

public class QueryCatalog {

	/*******************/
	// QUERY DI SELECT //
	/*******************/
	// From cliente
		public static final String selectFromClienteByPrimaryKey = 
				"SELECT nominativo, codice_fiscale, indirizzo"
			  + "  FROM conto                                "
			  + " WHERE iban = ?                             ";
		
		public static final String selectFromClienteByNominativo = 
				"SELECT nominativo, codice_fiscale, indirizzo"
			  + "  FROM conto                                "
			  + " WHERE nominativo = ?                       ";
		
		public static final String selectFromClienteByNominativoLike = 
				"SELECT nominativo, codice_fiscale, indirizzo"
			  + "  FROM conto                                "
			  + " WHERE nominativo LIKE ?                    ";
		
	// From conto
		public static final String selectFromContoByPrimaryKey = 
				"SELECT iban, valuta, codice_fiscale, scoperto, data_ora_intestazione"
			  + "  FROM conto                                                        "
			  + " WHERE iban = ?                                                     ";
		
		public static final String selectFromContoByPrimaryKeyLike = 
				"SELECT iban, valuta, codice_fiscale, scoperto, data_ora_intestazione"
			  + "  FROM conto                                                        "
			  + " WHERE iban LIKE ?                                                  ";
		
		public static final String selectFromContoByCodiceFiscale = 
				"SELECT iban, valuta, codice_fiscale, scoperto, data_ora_intestazione"
			  + "  FROM conto                                                        "
			  + " WHERE codice_fiscale = ?                                           ";
		
	// From movimento
		public static final String selectFromMovimentoByPrimaryKey = 
				"SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
			  + "  FROM movimento                                              "
			  + " WHERE id = ?                                                 ";
		
		public static final String selectFromMovimentoByIban = 
				"SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
			  + "  FROM movimento                                              "
			  + " WHERE iban = ?                                               ";
	
	/*******************/
	// QUERY DI INSERT //
	/*******************/
	// Into cliente
		public static final String insertCliente =
				"INSERT INTO cliente (nominativo, codice_fiscale, indirizzo)"
			  + "     VALUES (?, ?, ?)                                      ";
		
	// Into conto 
		public static final String insertConto =
				"INSERT INTO conto (iban, valuta, codice_fiscale, scoperto, data_ora_intestazione)"
			  +	"     VALUES (?, ?, ?, ?, ?)                                                      ";
		
	// Into movimento
		public static final String insertMovimento =
				"INSERT INTO conto (id, importo, tipo_operazione, iban, data_ora_operazione)"
			  +	"     VALUES (?, ?, ?, ?, ?)                                                ";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
