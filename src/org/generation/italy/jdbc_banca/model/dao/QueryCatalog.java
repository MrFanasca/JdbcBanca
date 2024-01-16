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
    
    public static final String selectFromClienteByNominativo =
            " SELECT codice_fiscale, nominativo, indirizzo	"
          + "   FROM cliente                                  "
          + "  WHERE cliente.nominativo = ?              	";
    
    public static final String selectFromContoByPrimaryKey =
    		" SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
    	  + "   FROM conto                                     							"
          + "  WHERE conto.iban = ?              											";
    
    public static final String selectFromContoByCodiceFiscale =
    		" SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
    	  + "   FROM conto                                     							"
          + "  WHERE conto.codice_fiscale = ?              											";

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
    public static final String selectFromClienteByNominativoLike =
            " SELECT codice_fiscale, nominativo, indirizzo	"
          + "   FROM cliente                                  "
          + "  WHERE cliente.nominativo LIKE ?              	";
    
    public static final String selectFromContoByIbanLike =
    		" SELECT iban, codice_fiscale, valuta, saldo, scoperto, data_ora_intestazione	"
    	  + "   FROM conto                                     							"
          + "  WHERE conto.iban LIKE ?              											";
          
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
	// ESERCIZIO 11/1
	// 1) seleziona i clienti che non hanno un conto
	
	public static final String selectFromClienteByIbanNull =
			" SELECT cl.codice_fiscale, cl.nominativo, cl.indirizzo "
		  + "   FROM cliente cl LEFT JOIN conto co ON cl.codice_fiscale = co.codice_fiscale "
		  + "  WHERE co.iban IS NULL ";
	
	
	// 2) seleziona tutti i conti del cliente con nominativo che contiene un determinato valore (confronta uso LIKE in QueryCatalog di JdbcMagazzino)
	
	public static final String selectFromContoByNominativoLike =
			" SELECT co.iban, co.codice_fiscale, co.valuta, co.scoperto, co.data_ora_intestazione "
		  + "   FROM conto co inner join cliente cl on co.codice_fiscale = cl.cod_fiscale "
		  + "  WHERE cl.nominativo LIKE ? ";
	
	
	// 3) selezionare tutti i conti: a) con un totale (versato o prelevato); b) in una certa valuta; c) per un importo inferiore ad una certa cifra
	
	public static final String selectFromContoByTotaleOperazioneValutaImportoMinore =
			" SELECT co.iban, co.codice_fiscale, co.valuta, co.scoperto, co.data_ora_intestazione, SUM(mo.importo) as importo_V_o_P "
		  + "   FROM conto co left join movimento mo ON co.iban = mo.iban "
		  + "  WHERE mo.tipo_operazione = ? "
		  + "    AND co.valuta = ? "
		  + "  GROUP BY mo.iban "
		  + " HAVING importo_V_o_P < ? "
		  + "    AND importo_V_o_P IS NOT NULL ";
	
	
	// 4) aggiornare tutti i conti in una certa valuta, che hanno uno scoperto superiore ad una cifra X, ponendo la data ed ora di intestazione ad un valore a 
	//    piacere	
	
	public static final String updateContoSetDataOraIntestazioneByScopertoValuta =
			" UPDATE conto "
		  + "    SET data_ora_intestazione = ? "
		  + "  WHERE scoperto > ? "
		  + "    AND valuta = ? ";
	
    // 5.e) selezionare tutti i conti per un tipo di operazione (versamento o prelievo) con importo inferiore ad una certa cifra
	
	public static final String selectFromContoByTipoOperazioneValutaImportoMinore =
			" SELECT co.iban, co.codice_fiscale, co.valuta, co.scoperto, co.data_ora_intestazione "
		  + "   FROM conto co LEFT JOIN movimento mo ON co.iban = mo.iban "
		  + "  WHERE mo.tipo_operazione = ? "
		  + "    AND mo.importo < ? "
		  + "    AND mo.importo IS NOT NULL "
		  + "  GROUP BY mo.iban "
		  + "    AND co.valuta = ? ";
		  
}
