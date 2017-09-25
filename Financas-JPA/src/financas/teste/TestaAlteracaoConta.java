package financas.teste;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Conta;

public class TestaAlteracaoConta {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(3);
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		
		em.getTransaction().begin();
		
		conta = em.find(Conta.class, conta.getId());
		conta.setAgencia("185");
		
		em.getTransaction().commit();
		em.close();
	}
	
}
