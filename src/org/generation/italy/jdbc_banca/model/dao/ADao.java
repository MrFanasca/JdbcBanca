package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;

public abstract class ADao {
	
	protected Connection jdbcConnectionToDatabase;								// attributo di riferimento per la connessione al database
	
	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}

}
