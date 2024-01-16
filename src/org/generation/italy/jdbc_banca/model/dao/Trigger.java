package org.generation.italy.jdbc_banca.model.dao;



import java.util.List;

import org.generation.italy.jdbc_banca.model.BancaModelConstants;
import org.generation.italy.jdbc_banca.model.BancaModelException;
import org.generation.italy.jdbc_banca.model.entity.Conto;
import org.generation.italy.jdbc_banca.model.entity.Movimento;

// Classe di verifica dei vincoli applicativi sulle operazioni di scrittura dei dati (TRIGGERS) nelle varie tabelle 

public class Trigger {
	
    /*************************/
    // DEFINIZIONE ATTRIBUTI //
    /*************************/
	public static ClienteDao clienteDao;
	public static ContoDao contoDao;
	public static MovimentoDao movimentoDao;
	
    /****************/
    //    METODI    //
    /****************/
    /**
     * Metodo trigger per la verifica dei vincoli:
     * 1) un movimento di prelievo può essere effettuato su un conto solo se, sommato al saldo corrente, non supera lo scoperto
     * 
     * @param movimento oggetto contenente i dettagli del movimento 
     * @throws BancaModelException 
     */
	 public static void checkBeforeInsertMovimento(Movimento movimento) throws BancaModelException {
		 

		 //1) un movimento di prelievo può essere effettuato su un conto solo se il suo importo, sottratto al saldo corrente, non supera lo scoperto
		 if (movimento.getTipoOperazione() == "P") {

			 //legge i dati del conto per ottenerne il saldo corrente
			 Conto conto = contoDao.loadContoByPrimaryKey(movimento.getIban());

			 if (Math.abs(conto.getSaldo() - movimento.getImporto()) > conto.getScoperto()) {

				 throw new BancaModelException ("Trigger -> checkBeforeInsertMovimento -> un movimento di prelievo può essere effettuato su un conto solo se, sommato al saldo corrente, non supera lo scoperto!");
			}
			 
		 }

		 //2) Un movimento può essere inserito solo se non sono stati già effettuati più di due movimenti sul conto 
		 List<Movimento> elencoMovimenti = movimentoDao.loadMovimentoByIban(movimento.getIban());

		 if (elencoMovimenti.size()>=BancaModelConstants.maxContiDiProprietaPerUnCliente)	{

			 throw new BancaModelException ("Trigger -> checkBeforeInsertMovimento -> sono qià stati effettuati due monimenti sul conto!");
		 }
	}
	 
	// 3) Implementare nella classe trigger un metodo che impedisca l'inserimento del conto per un cliente che ha già un massimo di conti
	public static void checkBeforeInsertConto(Conto conto) throws BancaModelException {
		 
		List<Conto> elencoConti = contoDao.loadContoByCodiceFiscale(conto.getCodiceFiscale());

		if (elencoConti.size()>=BancaModelConstants.maxContiDiProprietaPerUnCliente) {

		throw new BancaModelException ("Trigger -> checkBeforeInsertConto -> sono qià stati creati il numero massimo di conti per questo cliente!");
		}	 
	}
	
}
