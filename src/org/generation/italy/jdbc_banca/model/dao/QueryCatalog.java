package org.generation.italy.jdbc_banca.model.dao;

// Classe che raccoglie tutte le query utilizzate nei vari metodi DAO. Si centralizza il catalogo di modo da avere un 
// controllo globale su possibili errori sintattici e/o di uso delle query SQL

public class QueryCatalog {
	
	
	/*******************/
	// QUERY DI SELECT //
	/*******************/
	// Primary Key
    public static final String selectFromClienteByPrimaryKey =
            " SELECT codice_fiscale, nominativo, indirizzo	"
          + "   FROM cliente                                  "
          + "  WHERE cliente.codice_fiscale = ?              	";
    
    public static final String selectFromContoByPrimaryKey =
    		" SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
    	  + "   FROM conto                                     							"
          + "  WHERE conto.iban = ?              											";    

    public static final String selectFromMovimentoByPrimaryKey =
            " SELECT id_movimento, importo, tipo_operazione, iban, data_ora_operazione "
          + "   FROM movimento                                     					 "
          + "  WHERE movimento.id_movimento = ?              							 ";
    
    // Campo specifico
    public static final String selectFromMovimentoByIban =
            " SELECT id_movimento, importo, tipo_operazione, iban, data_ora_operazione "
          + "   FROM movimento                                     					 "
          + "  WHERE movimento.iban = ?              							 ";
    
    // Like
    
	/*******************/
	// QUERY DI INSERT //
	/*******************/
	public static final String insertCliente = 
			" INSERT INTO cliente (codice_fiscale, nominativo, indirizzo) "
		  + "      VALUES (?, ?, ?) ";

	public static final String insertConto =  
			" INSERT INTO conto (iban, codice_fiscale, valuta, scoperto) "
		  + "      VALUES (?, ?, ?, ?) ";
	
	public static final String insertMovimento = 
			" INSERT INTO movimento (iban, importo, tipo_operazione) "
		  + "      VALUES (?, ?, ?) ";

	/*********************/
	// QUERY DI ESERCIZI //
	/*********************/
    
}
