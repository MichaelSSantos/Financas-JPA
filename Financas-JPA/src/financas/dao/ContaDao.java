package financas.dao;

import javax.persistence.EntityManager;

import financas.modelo.Conta;

public class ContaDao {

	public void incluir(Conta conta) {//Conta até aqui é Transient, pois ainda não foi gerenciada pela JPA

		EntityManager em = Connection.geEntityManager("mysql_financas");

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		em.close();
	}
	
	public Conta findById(Integer id) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		
		em.getTransaction().begin();
		
		//A JPA ao recuperar o objeto Conta, o deixa em um estado chamado de managed.
		//Isso significa que o objeto está sincronizado com a tupla da relação no BD 
		//e que qualquer alteração no objeto irá resultar em um update.
		//NO ENTANTO, o estado managed existe apenas dentro de uma transação.
		Conta conta = em.find(Conta.class, id);
		
		//Esta alteração será persistida devido ao objeto está no estado managed.
		conta.setTitular("Rogério Patel Maia");
		
		//Persiste toda e qualquer alteração realizada no estado managed.
		em.getTransaction().commit();
		
		em.close();//Fecha o EntityManager e o objeto deixa de ser managed.
		return conta;
	}

}
