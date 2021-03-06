package financas.teste;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;
import financas.modelo.TipoMovimentacao;

public class TesteJPQL {

	public static void main(String[] args) {
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		//A movimenta��o se relaciona com uma conta.
		//Todo relacionamento se d� pela chave prim�ria na outra tabela.
		//Logo, o Hibernate ir� procurar o @Id na tabela relacionada.
		
		String jpql = "select m from Movimentacao m "
				+ "where m.conta = :pConta and m.tipo = :pTipo "
				+ "order by m.valor desc";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);//Named Parameter Notation :conta
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> resultado = query.getResultList();
		
		for (Movimentacao movimentacao : resultado) {
			System.out.printf("Valor: %.2f ", movimentacao.getValor());
			System.out.printf("Data: %d/%d/%d - ", movimentacao.getData().get(Calendar.DAY_OF_MONTH), movimentacao.getData().get(Calendar.MONTH), movimentacao.getData().get(Calendar.YEAR));
			System.out.printf("Cliente: %s\n", movimentacao.getConta().getBanco());
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
