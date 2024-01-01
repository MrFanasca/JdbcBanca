package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
				
			Integer scoperto = rsSelect.getInt("scoperto");
			if (rsSelect.wasNull()) {
				scoperto = 0;
			}
				
			LocalDate dataOraIntestazione = rsSelect.getDate("data_ora_intestazione").toLocalDate();
			if (rsSelect.wasNull()) {
				dataOraIntestazione = LocalDate.of(0, 0, 0); 
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
			
		String insertCliente =
				"INSERT INTO conto (iban, valuta, codice_fiscale, scoperto, data_ora_intestazione)"
			  +	"     VALUES (?, ?, ?, ?, ?)                                                      ";
			
		PreparedStatement preparedStatementInsertConto =
				this.jdbcConnectionToDatabase.prepareStatement(insertCliente);
			
		preparedStatementInsertConto.setString(1, conto.getIban());
		preparedStatementInsertConto.setString(2, conto.getValuta());
		preparedStatementInsertConto.setString(3, conto.getCodiceFiscale());
		preparedStatementInsertConto.setInt(4, conto.getScoperto());
		//preparedStatementInsertConto.setDate(5, conto.getDataOraIntestazione().toDate);
			
		preparedStatementInsertConto.executeQuery();
	}
}
