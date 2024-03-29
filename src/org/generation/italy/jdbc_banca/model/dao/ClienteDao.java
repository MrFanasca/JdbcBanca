package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_banca.model.BancaModelException;
import org.generation.italy.jdbc_banca.model.entity.Cliente;

// Classe per le operazioni CRUD (CREATE READ UPDATE DELETE) su tabella cliente

public class ClienteDao extends ADao {

	/***************/
    // COSTRUTTORE //
    /***************/	
	public ClienteDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}
	
    /**************************/
    // METODI DI LETTURA DATI //
    /**************************/
	/**
	 * Esecuzione di una query di SELECT con output i campi del record della tabella cliente
	 * 
	 * NOTA: il metodo private generalizza la necessità di caricare l'elenco ogni volta che si ha una SQL-SELECT su tabella 
	 *       cliente 
	 *  	  
	 * @param preparedstatement query SQL che ritorna dei record cliente
	 * @return elenco dei record cliente trovati
	 * 
	 * @throws BancaModelException : eccezione normalizzata
	 */
	
	private List<Cliente> loadClientiByQuery(PreparedStatement preparedstatement) throws BancaModelException {

        List<Cliente> elencoClienti = new ArrayList<Cliente>();

        try {

            ResultSet rsSelect
                    = preparedstatement.executeQuery();

            while (rsSelect.next()) {

                String codFiscale = rsSelect.getString("codice_fiscale");
                if (rsSelect.wasNull()) {
                    codFiscale = "";
                }

                String nominativo
                        = rsSelect.getString("nominativo");
                if (rsSelect.wasNull()) {
                    nominativo = "";
                }

                String indirizzo = rsSelect.getString("indirizzo");
		        if (rsSelect.wasNull()) {
		        	indirizzo = "";
		        }
		        
                Cliente cliente = new Cliente(codFiscale, nominativo, indirizzo);                
                elencoClienti.add(cliente);

            }

        } catch (SQLException sqlException) {

            throw new BancaModelException(										// normalizzazione dell'eccezione SQLException
            		"ClienteDao -> loadCliente -> " + sqlException.getMessage());	  																	
        }
        
        return elencoClienti;
    }	
	
	// Query di SELECT con input la Primary Key
	public Cliente loadClienteByPrimaryKey(String codiceFiscale) throws BancaModelException {
        
        Cliente cliente = null;
        
        try {
        	
            List<Cliente> elencoClienti = new ArrayList<Cliente>();
                
            PreparedStatement preparedStatement = 
            		this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.selectFromClienteByPrimaryKey);

            preparedStatement.setString(1, codiceFiscale);
                      
            elencoClienti = loadClientiByQuery(preparedStatement);                                        

            if (elencoClienti.size() == 1) {
                cliente = elencoClienti.get(0);
            }
            
        } catch (SQLException sqlException) {                                  
           
            throw new BancaModelException("ClienteDao -> loadClienteByPrimaryKey -> " + sqlException.getMessage());
        }

        return cliente;
    }
	
	// Query di Query di SELECT con input iban (ESERCIZIO 11/1.1)
	public List<Cliente> loadClienteSenzaContoByIban () throws BancaModelException	{
		
		List<Cliente> clientiSenzaConto = new ArrayList <>();
		
		try {
			
			PreparedStatement preparedStatement =
					this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.selectFromClienteByIbanNull);
			
			clientiSenzaConto = loadClientiByQuery(preparedStatement);
			
			
		} catch (SQLException sqlException) {
			
			throw new BancaModelException("ClienteDao -> loadClienteSenzaContoByIban -> " + sqlException.getMessage());
		}
		
		return clientiSenzaConto;
	}
	
    /****************************/
    // METODI DI SCRITTURA DATI //
    /****************************/	
    public void addCliente(Cliente cliente) throws BancaModelException {
        
        try {           
            
            PreparedStatement preparedStatement = 
            		this.jdbcConnectionToDatabase.prepareStatement(QueryCatalog.insertCliente);
            
            preparedStatement.setString(1, cliente.getCodiceFiscale());
            preparedStatement.setString(2, cliente.getNominativo());            
            
            if (cliente.getIndirizzo() == null) {
            	preparedStatement.setNull(3, java.sql.Types.VARCHAR);
        	}
            
            else {
                preparedStatement.setString(3, cliente.getIndirizzo());            
            }
            
            preparedStatement.executeUpdate();
    
        } catch (SQLException sqlException) {
        	
            throw new BancaModelException("ClienteDao -> addCliente -> " + sqlException.getMessage());
            
        }
        
    }
	
}