package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_banca.model.BancaModelException;
import org.generation.italy.jdbc_banca.model.entity.Conto;
import org.generation.italy.jdbc_banca.model.entity.Movimento;

//Classe per le operazioni CRUD (CREATE READ UPDATE DELETE) su tabella movimento

public class MovimentoDao extends ADao {

	/***************/
    // COSTRUTTORE //
    /***************/		
	public MovimentoDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}

	
    /**************************/
    // METODI DI LETTURA DATI //
    /**************************/
	/**
	 * Esecuzione di una query di SELECT con output i campi del record della tabella Movimento
	 * 
	 * NOTA: il metodo private generalizza la ncessità di caricare l'elenco ogni volta che si ha una SQL-SELECT su tabella Movimento 
	 *  	  
	 * @param preparedstatement query SQL che ritorna dei record Movimento
	 * @return elenco dei record Movimento trovati
	 * 
	 * @throws BancaModelException : eccezione normalizzata
	 */
	
	private List<Movimento> loadMovimentiByQuery(PreparedStatement preparedstatement) throws BancaModelException {

        List<Movimento> elencoMovimenti = new ArrayList<Movimento>();

        try {
        	ResultSet rsSelect = preparedstatement.executeQuery();

            while (rsSelect.next()) {

                Long idMovimento = rsSelect.getLong("id_movimento");
                if (rsSelect.wasNull()) {
                	idMovimento = (long) 0; 
                }

                String iban = rsSelect.getString("iban");
                if (rsSelect.wasNull()) { 
                	iban = ""; 
                }

		        String tipoOperazione = rsSelect.getString("tipo_operazione");
		        if (rsSelect.wasNull()) {
		        	tipoOperazione = "";
		        }
		        
		        Float importo = rsSelect.getFloat("importo");
		        if (rsSelect.wasNull()) {
		        	importo = 0.0f;
		        }
		        
                LocalDateTime dataOraOperazione = rsSelect.getTimestamp("data_ora_operazione").toLocalDateTime();
		        if (rsSelect.wasNull()) {
		        	dataOraOperazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0));
		        }
		        
                Movimento Movimento = new Movimento(idMovimento, iban, importo, tipoOperazione, dataOraOperazione);

                elencoMovimenti.add(Movimento);

            }

        } catch (SQLException sqlException) {

            throw new BancaModelException(										// normalizzazione dell'eccezione SQLException
            		"MovimentoDao -> loadMovimentiByQuery -> " + sqlException.getMessage());
        }
        
        return elencoMovimenti;
    }		
	
	// Query di SELECT con input la Primary Key
	public Movimento loadMovimentoByPrimaryKey(Long idMovimento) throws BancaModelException {

		Movimento Movimento = null;

		try {

			List<Movimento> elencoMovimenti = new ArrayList<Movimento>();

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromMovimentoByPrimaryKey);

			preparedStatement.setLong(1, idMovimento);

			elencoMovimenti = loadMovimentiByQuery(preparedStatement);

			if (elencoMovimenti.size() == 1) {
				Movimento = elencoMovimenti.get(0);
			}

		} catch (SQLException sqlException) {

			throw new BancaModelException("MovimentoDao -> loadMovimentoByPrimaryKey -> " + sqlException.getMessage());
		}

		return Movimento;
	}	
	
	// Query di SELECT con input l' iban
	public List<Movimento> loadMovimentoByIban(String iban) throws BancaModelException {
		
		List<Movimento> elencoMovimenti = new ArrayList<Movimento>();

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromMovimentoByIban);

			preparedStatement.setString(1, iban);

			elencoMovimenti = loadMovimentiByQuery(preparedStatement);

		} catch (SQLException sqlException) {

			throw new BancaModelException("MovimentoDao -> loadMovimentoByIban -> " + sqlException.getMessage());
		}

		return elencoMovimenti;		
	}
	
	
    /****************************/
    // METODI DI SCRITTURA DATI //
    /****************************/	
    public void addMovimento(Movimento movimento) throws BancaModelException {
        
        try {  
        	
        	this.jdbcConnectionToDatabase.setAutoCommit(false);
        	
        	Trigger.checkBeforeInsertMovimento(movimento);
        	
        	ContoDao contoDao = new ContoDao(this.jdbcConnectionToDatabase);
        	Conto conto = contoDao.loadContoByPrimaryKey(movimento.getIban());        	
                  
            PreparedStatement preparedStatement = 
            		this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.insertMovimento);
            
            preparedStatement.setString(1, movimento.getIban());
            preparedStatement.setFloat(2, movimento.getImporto());            
            preparedStatement.setString(3, movimento.getTipoOperazione()); 
            
            Float nuovoSaldo;
            
            if (movimento.getTipoOperazione().equals("V")) {            
            	nuovoSaldo = conto.getSaldo() + movimento.getImporto();
            }
            else {
            	nuovoSaldo = conto.getSaldo() - movimento.getImporto();
            }
            
             PreparedStatement preparedStatement1 =
            		this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.updateContoSetSaldoByIban);
            
            preparedStatement1.setFloat(1, nuovoSaldo);
            preparedStatement1.setString(2, movimento.getIban());
            
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();
            
            this.jdbcConnectionToDatabase.commit();
            
            this.jdbcConnectionToDatabase.setAutoCommit(true);
    
        } catch (SQLException sqlException) {
        	
            throw new BancaModelException("MovimentoDao -> addMovimento -> " + sqlException.getMessage());            
        }
        
	} 	
	
}
