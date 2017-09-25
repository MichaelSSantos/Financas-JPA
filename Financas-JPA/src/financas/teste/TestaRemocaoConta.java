package financas.teste;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Conta;

public class TestaRemocaoConta {

	public static void main(String[] args) {
		
		//Estado: transient
		Conta conta = new Conta();
		conta.setId(5);
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		//Primeiro preciso buscar e tornar o objeto managed.
		//Ou seja, gerenciado pela JPA.
		conta = em.find(Conta.class, 5);
		
		//Agora consigo fazer a exclusão de um objeto managed.
		em.remove(conta);//Estado: Removed (Continua existindo no heap)
		
		em.getTransaction().commit();
		em.close();//Estado: Detached
		
	}
	
}
