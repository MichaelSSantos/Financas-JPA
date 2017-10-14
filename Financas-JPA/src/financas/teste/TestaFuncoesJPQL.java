package financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.TipoMovimentacao;

public class TestaFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager em = Connection.geEntityManager(Connection.FINANCAS);
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(1);

		/*
		 * Funções:
		 * sum(m.valor)
		 * avg(m.valor) -> Retorna Double
		 * max(m.valor)
		 * min(m.valor)
		 * count(m) -> Long
		 * ...
		 */
		
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);

		BigDecimal soma = (BigDecimal) query.getSingleResult(); 

		System.out.println("A soma é " + soma);
		
		em.getTransaction().commit();
		em.close();

	}

}
