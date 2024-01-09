package org.generation.italy.jdbc_banca.model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.generation.italy.jdbc_banca.model.dao.ADao;
import org.generation.italy.jdbc_banca.model.dao.ClienteDao;
import org.generation.italy.jdbc_banca.model.dao.ContoDao;
import org.generation.italy.jdbc_banca.model.dao.MovimentoDao;

public class TestJdbcBancaDao extends ADao {
	
	// ATTRIBUTI
	ClienteDao clienteDao;
	ContoDao contoDao;
	MovimentoDao movimentoDao;

	// COSTRUTTORE
	public TestJdbcBancaDao(Connection jdbcConnectionToDatabase, ClienteDao clienteDao, ContoDao contoDao, MovimentoDao movimentoDao) 
				throws SQLException	{
		super(jdbcConnectionToDatabase);
		this.clienteDao = clienteDao;
		this.contoDao = contoDao;
		this.movimentoDao = movimentoDao;
	}

	// METODI
	public void removeAllMovimento() throws SQLException	{
		
		String deleteAllMovimenti =
				" DELETE FROM movimento";
		
		PreparedStatement preparedStatementDeleteMovimento =
				this.jdbcConnectionToDatabase.prepareStatement(deleteAllMovimenti);
		
		preparedStatementDeleteMovimento.executeQuery();
	}
}
