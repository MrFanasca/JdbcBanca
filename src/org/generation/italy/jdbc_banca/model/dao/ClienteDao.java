package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.generation.italy.jdbc_banca.model.entity.Cliente;

// Classe per le operazioni CRUD (CREATE READ UPDATE DELETE) su tabella cliente

public class ClienteDao extends ADao{

	public ClienteDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}
	
	// load => SELECT
	/**
	  // SELECT nominativo, codice_fiscale, indirizzo
	  //   FROM cliente
	  //  WHERE codice_fiscale = ?
	* @param codice_fiscale codice identificatifo del cliente
	* @return oggetto di tipo classe cliente
	* @throws SQLException
	*/
	public Cliente loadClienteByPrimaryKey (String codiceFiscale)	throws SQLException {
				
		String selectFromClienteByPrimaryKey = 
				"SELECT nominativo, codice_fiscale, indirizzo"
			  + "  FROM conto                                "
			  + " WHERE iban = ?                             ";
				
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromClienteByPrimaryKey);
				
		preparedStatement.setString(1, codiceFiscale);
				
		ResultSet rsSelect = 
				preparedStatement.executeQuery();
				
		Cliente clienteTrovato = null;
				
		if (rsSelect.next())	{
					
			String nominativo = rsSelect.getString("iban");
			if(rsSelect.wasNull()) {
				nominativo = "";
			}
					
			String codFiscale = rsSelect.getString("valuta");
			if(rsSelect.wasNull()) {
				codFiscale = "";
			}
					
			String indirizzo = rsSelect.getString("codice_fiscale");
			if (rsSelect.wasNull()) {
				indirizzo = "";
				}
					
			clienteTrovato = new Cliente (nominativo, codFiscale, indirizzo);
		}
						
		return clienteTrovato;
	}
	
	// add => INSERT
	/**
	 // INSERT INTO cliente (nominativo, codice_fiscale, indirizzo)
	 //      VALUES (?, ?, ?)
	 * @param cliente oggetto di tipo Cliente da inserire
	 * @throws SQLException
	 */
	public void addCliente (Cliente cliente) throws SQLException {
		
		String insertCliente =
				"INSERT INTO cliente (nominativo, codice_fiscale, indirizzo)"
			  +	"     VALUES (?, ?, ?)                                      ";
		
		PreparedStatement preparedStatementInsertCliente =
				this.jdbcConnectionToDatabase.prepareStatement(insertCliente);
		
		preparedStatementInsertCliente.setString(1, cliente.getNominativo());
		preparedStatementInsertCliente.setString(2, cliente.getCodiceFiscale());
		preparedStatementInsertCliente.setString(3, cliente.getIndirizzo());
		
		preparedStatementInsertCliente.executeQuery();
	}
	
	// remove => DELETE FROM
	/**
		// DELETE FROM cliente
		//		 WHERE codice_fiscale = ?
	 * @param codFiscale codice identificativo del cliente da eliminare
	 * @throws SQLException
	 */
	public void removeClienteByPrimaryKey (String codFiscale) throws SQLException {

		String deleteCliente =
				"DELETE FROM cliente           "
			  + "      WHERE codice_fiscale = ?";

		PreparedStatement preparedStatementDeleteCliente =
				this.jdbcConnectionToDatabase.prepareStatement(deleteCliente);

		preparedStatementDeleteCliente.setString(1, codFiscale);

		preparedStatementDeleteCliente.executeQuery();
	}	
}
