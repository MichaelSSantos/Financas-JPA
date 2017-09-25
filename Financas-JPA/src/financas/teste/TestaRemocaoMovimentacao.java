package financas.teste;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Movimentacao;

public class TestaRemocaoMovimentacao {

	public static void main(String[] args) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		Movimentacao movimentacao = em.find(Movimentacao.class, 3);
		em.remove(movimentacao);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
