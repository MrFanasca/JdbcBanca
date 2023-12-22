package org.generation.italy.jdbc_banca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.generation.italy.jdbc_banca.model.dao.ClienteDao;
import org.generation.italy.jdbc_banca.model.dao.ContoDao;
import org.generation.italy.jdbc_banca.model.dao.MovimentoDao;

public class JdbcCrudMain {
	public static void main(String[] args) {
		
		
		// Connessione al database
		String databaseName = "banca";											//nome del database a cui connettersi
		String dbmsUserName = "root";											//nome utente configurato nel dbms
		String dbmsPassword = "";												//password utente configurato nel dbms
		String jdbcUrl = "jdbc:mariadb://localhost:3306/" + databaseName;	
		
		
		
		try {																	//prova ad eseguire le istruzioni interne al blocco try-catch

			
			/****************************************************************************/
			/*					CONNESSIONE AL DATABASE									*/
			/****************************************************************************/
			
			
			Connection jdbcConnectionToDatabase = 								//esegue la connessione al dbms con riferimento al database, se fallisce genera eccezzione SQLException (effettuare il debugging per verificarlo)
					DriverManager.getConnection(jdbcUrl
											  , dbmsUserName
											  , dbmsPassword);
			
			ClienteDao clienteDao = new ClienteDao (jdbcConnectionToDatabase);
			ContoDao contoDao = new ContoDao (jdbcConnectionToDatabase);
			MovimentoDao movimentoDao = new MovimentoDao (jdbcConnectionToDatabase);
			
			System.out.println("Connessione al database magazzino riuscita!");	//visualizza messaggio per avvenuta connessione al database
			
		} catch (SQLException e) {												//errore di tipo classe SQLException
			// TODO Auto-generated catch block
			//e.printStackTrace();												//stampa la pila (stack) degli errori, dal più recente al meno recente
			System.out.println(e.getMessage()); 								//stampa lo sepcifico messaggio di errore
			//throw new JdbcMagazzinoException();
		}
	}	
}
