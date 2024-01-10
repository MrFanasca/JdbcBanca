package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.generation.italy.jdbc_banca.model.entity.Cliente;
import org.generation.italy.jdbc_banca.model.entity.Conto;

public class ContoDao extends ADao{

	public ContoDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}

	// load => SELECT
	/**
	   // SELECT iban, valuta, codice_fiscale, scoperto, data_ora_intestazione
	   //   FROM conto
	   //  WHERE iban = ?
	 * @param iban codice identificatifo del conto
	 * @return oggetto di tipo classe conto
	 * @throws SQLException
	 */
	public Conto loadContoByPrimaryKey (String iban)	throws SQLException {
			
		String selectFromContoByPrimaryKey = 
				"SELECT iban, valuta, codice_fiscale, scoperto, data_ora_intestazione"
			  + "  FROM conto                                                        "
			  + " WHERE iban = ?                                                     ";
			
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromContoByPrimaryKey);
			
		preparedStatement.setString(1, iban);
			
		ResultSet rsSelect = 
				preparedStatement.executeQuery();
			
		Conto contoTrovato = null;
			
		if (rsSelect.next())	{
				
			String iban1 = rsSelect.getString("iban");
			if(rsSelect.wasNull()) {
				iban1 = "";
			}
				
			String valuta = rsSelect.getString("valuta");
			if(rsSelect.wasNull()) {
				valuta = "";
			}
				
			String codiceFiscale = rsSelect.getString("codice_fiscale");
			if (rsSelect.wasNull()) {
				codiceFiscale = "";
			}
				
			Float scoperto = rsSelect.getFloat("scoperto");
			if (rsSelect.wasNull()) {
				scoperto = 0.0f;
			}
				
			LocalDateTime dataOraIntestazione = rsSelect.getTimestamp("data_ora_intestazione").toLocalDateTime();
			if (rsSelect.wasNull()) {
				dataOraIntestazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0)); 
			}
				
			contoTrovato = new Conto (iban1, valuta, codiceFiscale, scoperto, dataOraIntestazione);
		}
					
		return contoTrovato;
	}
		
	// add => INSERT
	/**
	 // INSERT INTO conto (iban, valuta, codice_fiscale, scoperto, data_ora_intestazione)
	 //      VALUES (?, ?, ?, ?, ?)
	 * @param conto oggetto di tipo Conto da inserire
	 * @throws SQLException
	 */
	public void addConto (Conto conto) throws SQLException {
			
		String insertConto =
				"INSERT INTO conto (iban, valuta, codice_fiscale, scoperto, data_ora_intestazione)"
			  +	"     VALUES (?, ?, ?, ?, ?)                                                      ";
			
		PreparedStatement preparedStatementInsertConto =
				this.jdbcConnectionToDatabase.prepareStatement(insertConto);
			
		preparedStatementInsertConto.setString(1, conto.getIban());
		preparedStatementInsertConto.setString(2, conto.getValuta());
		preparedStatementInsertConto.setString(3, conto.getCodiceFiscale());
		preparedStatementInsertConto.setFloat(4, conto.getScoperto());
		//preparedStatementInsertConto.setDate(5, conto.getDataOraIntestazione().toDate);
			
		preparedStatementInsertConto.executeQuery();
	}
	
	// remove => DELETE FROM
	/**
	// DELETE FROM conto
	//		  WHERE iban = ?
	* @param iban numero identificativo del conto da eliminare
	* @throws SQLException
	*/
	public void removeContoByPrimaryKey (String iban) throws SQLException {
			
		String deleteConto =
				"DELETE FROM conto   "
			  + "      WHERE iban = ?";
			
		PreparedStatement preparedStatementDeleteConto =
				this.jdbcConnectionToDatabase.prepareStatement(deleteConto);
			
		preparedStatementDeleteConto.setString(1, iban);
			
		preparedStatementDeleteConto.executeQuery();
	}
}
