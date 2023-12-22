package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.jdbc_banca.model.entity.Movimento;

public class MovimentoDao extends ADao{

	public MovimentoDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}

	/**
	 *  SELECT iban, codice_fiscale, importo
	 *    FROM movimento
	 *   WHERE tipo_operazione = "p"
	 *     AND importo > 550;
	 */
	
	public List<Movimento> loadMovimentoByTipoOperazioneAndImportoOver (String tipoOperazione, Float importo)	 throws SQLException {
		
		String selectFromMovimentoByTipoOperazione = "SELECT iban, codice_fiscale, importo "
												   + " FROM movimento                     "
												   + "WHERE tipo_operazione = ?           "
												   + "  AND importo > ?                   ";
		
		PreparedStatement preparedStatement = this.jdbcConnectionToDatabase.prepareStatement(selectFromMovimentoByTipoOperazione);
		
		preparedStatement.setString(1, tipoOperazione);
		preparedStatement.setFloat(2, importo);
		
		ResultSet rsSelect = preparedStatement.executeQuery();
		
		List<Movimento> listaMovimenti = new ArrayList <>();
		
		while (rsSelect.next())	{
			
			Integer id = rsSelect.getInt("id_movimento");
			if (rsSelect.wasNull())	{
				id = 0;
			}
			
			String codiceFiscale = rsSelect.getString("codice_fiscale");
			if (rsSelect.wasNull())	{
				codiceFiscale = "";
			}
			
			String iban = rsSelect.getString("iban");
			if (rsSelect.wasNull())	{
				iban = "";
			}
			
			Float importo1 = rsSelect.getFloat("importo");
			if (rsSelect.wasNull())	{
				importo = 0f;
			}
			
			Movimento movimentoLetto = new Movimento(null, importo1, selectFromMovimentoByTipoOperazione, iban);
			
			listaMovimenti.add
		}
		
		return listaMovimenti;
	}
}
