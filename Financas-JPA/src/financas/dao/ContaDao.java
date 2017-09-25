package financas.dao;

import javax.persistence.EntityManager;

import financas.modelo.Conta;

public class ContaDao {

	public void incluir(Conta conta) {//Conta at� aqui � Transient, pois ainda n�o foi gerenciada pela JPA

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
		//Isso significa que o objeto est� sincronizado com a tupla da rela��o no BD 
		//e que qualquer altera��o no objeto ir� resultar em um update.
		//NO ENTANTO, o estado managed existe apenas dentro de uma transa��o.
		Conta conta = em.find(Conta.class, id);
		
		//Esta altera��o ser� persistida devido ao objeto est� no estado managed.
		conta.setTitular("Rog�rio Patel Maia");
		
		//Persiste toda e qualquer altera��o realizada no estado managed.
		em.getTransaction().commit();
		
		em.close();//Fecha o EntityManager e o objeto deixa de ser managed.
		return conta;
	}

}
