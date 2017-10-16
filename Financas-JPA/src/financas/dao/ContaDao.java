package financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	//Com NamedQuery: Criação de query dentro do bean
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
