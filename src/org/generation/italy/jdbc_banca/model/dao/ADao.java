package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;

public abstract class ADao {

    /*************************/
    // DEFINIZIONE ATTRIBUTI //
    /*************************/
	protected Connection jdbcConnectionToDatabase;								
	
	/***************/
    // COSTRUTTORE //
    /***************/
	public ADao(Connection jdbcConnectionToDatabase) {
		this.jdbcConnectionToDatabase = jdbcConnectionToDatabase;
	}

}
