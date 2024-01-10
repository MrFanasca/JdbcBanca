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

import org.generation.italy.jdbc_banca.model.entity.Movimento;

public class MovimentoDao extends ADao{

	public MovimentoDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}

	// load => SELECT
	/**
	   // SELECT iban, codice_fiscale, importo, tipo_operazione
	   //   FROM movimento
	   //  WHERE id = ?
	 * @param idMovimento numero identificatifo del movimento
	 * @return oggetto di tipo classe movimento
	 * @throws SQLException
	 */
	public Movimento loadMovimentoByPrimaryKey (Long idMovimento)	throws SQLException {
		
		String selectFromMovimentoByPrimaryKey = 
				"SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
			  + "  FROM movimento                                              "
			  + " WHERE id = ?                                                 ";
		
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromMovimentoByPrimaryKey);
		
		preparedStatement.setLong(1, idMovimento);
		
		ResultSet rsSelect = 
				preparedStatement.executeQuery();
		
		Movimento movimentoTrovato = null;
		
		if (rsSelect.next())	{
			
			Long id = rsSelect.getLong("id");
			if (rsSelect.wasNull())	{
				id = (long) 0;
			}
			
			Float importo = rsSelect.getFloat("importo");
			if (rsSelect.wasNull()) {
				importo = 0.0f;
			}
			
			String tipoOperazione = rsSelect.getString("tipo_operazione");
			if (rsSelect.wasNull()) {
				tipoOperazione = "";
			}
			
			String iban = rsSelect.getString("iban");
			if(rsSelect.wasNull()) {
				iban = "";
			}
			
			LocalDateTime dataOraOperazione = rsSelect.getTimestamp("data_ora_operazione").toLocalDateTime();
			if (rsSelect.wasNull()) {
				dataOraOperazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0)); 
			}
			
			movimentoTrovato = new Movimento (id, importo, tipoOperazione, iban, dataOraOperazione);
		}
				
		return movimentoTrovato;
	}
	
	/**
	   // SELECT iban, codice_fiscale, importo, tipo_operazione
	   //   FROM movimento
	   //  WHERE iban = ?
	 * @param ibanConto numero identificatifo del conto
	 * @return oggetto di tipo classe movimento
	 * @throws SQLException
	 */
	public Movimento loadMovimentoByIban (String ibanConto)	throws SQLException {
		
		String selectFromMovimentoByIban = 
				"SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
			  + "  FROM movimento                                              "
			  + " WHERE iban = ?                                                 ";
		
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromMovimentoByIban);
		
		preparedStatement.setString(1, ibanConto);
		
		ResultSet rsSelect = 
				preparedStatement.executeQuery();
		
		Movimento movimentoTrovato = null;
		
		if (rsSelect.next())	{
			
			Long id = rsSelect.getLong("id");
			if (rsSelect.wasNull())	{
				id = (long) 0;
			}
			
			Float importo = rsSelect.getFloat("importo");
			if (rsSelect.wasNull()) {
				importo = 0.0f;
			}
			
			String tipoOperazione = rsSelect.getString("tipo_operazione");
			if (rsSelect.wasNull()) {
				tipoOperazione = "";
			}
			
			String iban = rsSelect.getString("iban");
			if(rsSelect.wasNull()) {
				iban = "";
			}
			
			LocalDateTime dataOraOperazione = rsSelect.getTimestamp("data_ora_operazione").toLocalDateTime();
			if (rsSelect.wasNull()) {
				dataOraOperazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0)); 
			}
			
			movimentoTrovato = new Movimento (id, importo, tipoOperazione, iban, dataOraOperazione);
		}
				
		return movimentoTrovato;
	}
	
	/**
	 *  SELECT iban, codice_fiscale, importo
	 *    FROM movimento
	 *   WHERE tipo_operazione = "p"
	 *     AND importo > 550;
	 */
	public List<Movimento> loadMovimentoByTipoOperazioneAndImportoOver (String tipoOperazione, Float importo)	 throws SQLException {
		
		String selectFromMovimentoByTipoOperazione = "SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
												   + " FROM movimento                                               "
												   + "WHERE tipo_operazione = ?                                     "
												   + "  AND importo > ?                                             ";
		
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromMovimentoByTipoOperazione);
		
		preparedStatement.setString(1, tipoOperazione);
		preparedStatement.setFloat(2, importo);
		
		ResultSet rsSelect = preparedStatement.executeQuery();
		
		List<Movimento> listaMovimenti = new ArrayList <>();
		
		while (rsSelect.next())	{
			
			Long id = rsSelect.getLong("id");
			if (rsSelect.wasNull())	{
				id = (long) 0;
			}
			
			Float importo1 = rsSelect.getFloat("importo");
			if (rsSelect.wasNull())	{
				importo = 0f;
			}
			
			String tipoOperazione1 = rsSelect.getString("tipo_operazione");
			if (rsSelect.wasNull())	{
				tipoOperazione1 = "";
			}
			
			String iban = rsSelect.getString("iban");
			if (rsSelect.wasNull())	{
				iban = "";
			}
			
			LocalDateTime dataOraOperazione = rsSelect.getTimestamp("data_ora_operazione").toLocalDateTime();
			if (rsSelect.wasNull()) {
				dataOraOperazione = LocalDateTime.of(LocalDate.of(0,0,0), LocalTime.of(0, 0, 0)); 
			}
			
			Movimento movimentoLetto = new Movimento(id, importo1, tipoOperazione1, iban, dataOraOperazione);
			
			listaMovimenti.add(movimentoLetto);
		}
		
		return listaMovimenti;
	}

	// add => INSERT
	/**
	 // INSERT INTO movimento (iban, valuta, codice_fiscale, scoperto, data_ora_intestazione)
	 //      VALUES (?, ?, ?, ?, ?)
	 * @param movimento oggetto di tipo Movimento da inserire
	 * @throws SQLException
	 */
	public void addMovimento (Movimento movimento) throws SQLException {
			
		String insertMovimento =
				"INSERT INTO conto (id, importo, tipo_operazione, iban, data_ora_operazione)"
			  +	"     VALUES (?, ?, ?, ?, ?)                                                ";
			
		PreparedStatement preparedStatementInsertMovimento =
				this.jdbcConnectionToDatabase.prepareStatement(insertMovimento);
			
		preparedStatementInsertMovimento.setLong(1, movimento.getId());
		preparedStatementInsertMovimento.setFloat(2, movimento.getImporto());
		preparedStatementInsertMovimento.setString(3, movimento.getTipoOperazione());
		preparedStatementInsertMovimento.setString(4, movimento.getIban());
		//preparedStatementInsertMovimento.setDate(5, movimento.getDataOraOperazione().toDate);
			
		preparedStatementInsertMovimento.executeQuery();
	}
	
	// remove => DELETE FROM
	/**
	 // DELETE FROM movimento
	 //		  WHERE id = ?
	 * @param idMovimento numero identificativo del movimento da eliminare
	 * @throws SQLException
	 */
	public void removeMovimentoByPrimaryKey (int idMovimento) throws SQLException {
		
		String deleteMovimento =
				"DELETE FROM movimento"
			  + "      WHERE id = ?   ";
		
		PreparedStatement preparedStatementDeleteMovimento =
				this.jdbcConnectionToDatabase.prepareStatement(deleteMovimento);
		
		preparedStatementDeleteMovimento.setInt(1, idMovimento);
		
		preparedStatementDeleteMovimento.executeQuery();
	}
	
	/**
	 // DELETE FROM movimento
	 //		  WHERE iban = ?
	 * @param ibanConto numero identificativo del conto dal quale eliminare i movimenti
	 * @throws SQLException
	 */
	public void removeMovimentoByIban (String ibanConto) throws SQLException {
		
		String deleteMovimento =
				"DELETE FROM movimento"
			  + "      WHERE iban = ?   ";
		
		PreparedStatement preparedStatementDeleteMovimento =
				this.jdbcConnectionToDatabase.prepareStatement(deleteMovimento);
		
		preparedStatementDeleteMovimento.setString(1, ibanConto);
		
		preparedStatementDeleteMovimento.executeQuery();
	}
}
