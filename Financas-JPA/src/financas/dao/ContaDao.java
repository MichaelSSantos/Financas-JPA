package financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	public void update(Conta conta) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		Conta tmp = em.find(Conta.class, conta.getId());
		tmp.setAgencia(conta.getAgencia());
		tmp.setNumero(conta.getNumero());
		tmp.setBanco(conta.getBanco());
		tmp.setTitular(conta.getTitular());
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public void remove(Conta conta) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		em.remove(em.find(Conta.class, conta.getId()));
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Conta> findAll() {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		String jpql = "select c from Conta c";
		Query query = em.createQuery(jpql);
		List<Conta> contas = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return contas;
	}
	
	//Sem TypedQuery e sem NamedQuery
	public List<Conta> findByAgenciaOld(Conta conta){
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		String jpql = "select c from Conta c where c.agencia = :pAgencia";
		Query query = em.createQuery(jpql);
		query.setParameter("pAgencia", conta.getAgencia());
		List<Conta> contas = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return contas;
	}

	//Com TypedQuery: Especifica o tipo de retorno
	//Com NamedQuery: Cria��o de query dentro do bean
	public List<Conta> findByAgencia(Conta conta){
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		TypedQuery<Conta> query = em.createNamedQuery("findByAgencia", Conta.class);
		query.setParameter("pAgencia", conta.getAgencia());
		List<Conta> contas = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return contas;
	}
	
	public static void main(String[] args) {
		ContaDao contaDao = new ContaDao();
	
		Conta conta = new Conta();
		conta.setAgencia("112");
		
		for (Conta c : contaDao.findByAgencia(conta)) {
			System.out.println(c.toString());
		}
		
	}
	
}
