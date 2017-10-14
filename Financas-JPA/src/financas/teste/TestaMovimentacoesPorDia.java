package financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.TipoMovimentacao;

public class TestaMovimentacoesPorDia {

	public static void main(String[] args) {

		EntityManager em = Connection.geEntityManager(Connection.FINANCAS);
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		/*
		 * Retorna a m�dia de valores da movimenta��o por dia.
		 * Group by em data e retorna a m�dia de valores de cada data.
		 */
		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo"
				+ " group by m.data";
		
		//TypedQuery: Forma de assegurar que o retorno da lista � um tipo espec�fico.
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Double> medias = query.getResultList();

		for (Double media : medias) {
			System.out.println("A m�dia �: " + media);
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
