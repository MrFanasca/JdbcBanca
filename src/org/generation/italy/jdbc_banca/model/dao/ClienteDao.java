package org.generation.italy.jdbc_banca.model.dao;

import java.sql.Connection;

public class ClienteDao extends ADao{

	public ClienteDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
	}
	
}
