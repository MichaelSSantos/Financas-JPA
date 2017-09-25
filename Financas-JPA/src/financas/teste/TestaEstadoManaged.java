package financas.teste;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Conta;

public class TestaEstadoManaged {
	
	public static void main(String[] args) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		
		em.getTransaction().begin();
		//Conta: Managed (Sincronizada com o BD)
		Conta conta = em.find(Conta.class, 2);
		conta.setTitular("Pedro Vianna de Souza");
		
		em.getTransaction().commit();
		em.close();//Conta: Detached (Sincronização encerrada)
		
		EntityManager em2 = Connection.geEntityManager("mysql_financas");
		
		em2.getTransaction().begin();
		
		conta.setTitular("Laércio Silva Gomes");
		//Erro, porque o objeto conta está Detached. 
//		em2.persist(conta);
		
		//Meio para transformar conta em Managed novamente.
		em2.merge(conta);
		
		em2.getTransaction().commit();
		em2.close();
		
	}

}
