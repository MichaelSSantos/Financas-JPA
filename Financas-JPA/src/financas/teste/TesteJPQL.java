package financas.teste;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;

public class TesteJPQL {

	public static void main(String[] args) {
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		//A movimentação se relaciona com uma conta.
		//Todo relacionamento se dá pela chave primária na outra tabela.
		//Logo, o Hibernate irá procurar o @Id na tabela relacionada.
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";//named paramter :conta
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		
		List<Movimentacao> resultado = query.getResultList();
		
		for (Movimentacao movimentacao : resultado) {
			System.out.printf("Data: %d/%d/%d - ", movimentacao.getData().get(Calendar.DAY_OF_MONTH), movimentacao.getData().get(Calendar.MONTH), movimentacao.getData().get(Calendar.YEAR));
			System.out.printf("Cliente: %s\n", movimentacao.getConta().getBanco());
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
