package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_banca.model.entity.Conto;
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
	 * @param id numero identificatifo del movimento
	 * @return oggetto di tipo classe movimento
	 * @throws SQLException
	 */
	public Movimento loadMovimentoByPrimaryKey (Integer id)	throws SQLException {
		
		String selectFromMovimentoByPrimaryKey = 
				"SELECT id, importo, tipo_operazione, iban, data_ora_operazione"
			  + "  FROM movimento                                              "
			  + " WHERE id = ?                                                 ";
		
		PreparedStatement preparedStatement = 
				this.jdbcConnectionToDatabase.prepareStatement(selectFromMovimentoByPrimaryKey);
		
		preparedStatement.setInt(1, id);
		
		ResultSet rsSelect = 
				preparedStatement.executeQuery();
		
		Movimento movimentoTrovato = null;
		
		if (rsSelect.next())	{
			
			Integer id1 = rsSelect.getInt("id_movimento");
			if (rsSelect.wasNull())	{
				id1 = 0;
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
			
			LocalDate dataOraOperazione = rsSelect.getDate("data_ora_operazione").toLocalDate();
			if (rsSelect.wasNull()) {
				dataOraOperazione = LocalDate.of(0, 0, 0); 
			}
			
			movimentoTrovato = new Movimento (id1, importo, tipoOperazione, iban, dataOraOperazione);
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
			
			Integer id = rsSelect.getInt("id_movimento");
			if (rsSelect.wasNull())	{
				id = 0;
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
			
			LocalDate  dataOraOperazione = rsSelect.getDate("data_ora_operazione").toLocalDate();
			if (rsSelect.wasNull())	{
				dataOraOperazione = LocalDate.of(0, 0, 0);
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
			
		preparedStatementInsertMovimento.setInt(1, movimento.getId());
		preparedStatementInsertMovimento.setFloat(2, movimento.getImporto());
		preparedStatementInsertMovimento.setString(3, movimento.getTipoOperazione());
		preparedStatementInsertMovimento.setString(4, movimento.getIban());
		//preparedStatementInsertMovimento.setDate(5, movimento.getDataOraOperazione().toDate);
			
		preparedStatementInsertMovimento.executeQuery();
	}	
}
