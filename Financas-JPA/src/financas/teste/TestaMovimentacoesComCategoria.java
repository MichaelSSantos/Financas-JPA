package financas.teste;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Categoria;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;
import financas.modelo.TipoMovimentacao;

public class TestaMovimentacoesComCategoria {

	public static void main(String[] args) {
		
		/*
		 * A Conta 2 fez duas movimentações e cada movimentação teve duas categorias. 
		 */
		
		Categoria categoria1 = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Negócio");
	
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance());
		movimentacao1.setDescricao("Viagem à SC");
		movimentacao1.setValor(new BigDecimal("600.0"));
		movimentacao1.setTipo(TipoMovimentacao.SAIDA);
		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));

		movimentacao1.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setData(Calendar.getInstance());
		movimentacao2.setDescricao("Viagem à RS");
		movimentacao2.setValor(new BigDecimal("800.0"));
		movimentacao2.setTipo(TipoMovimentacao.SAIDA);
		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));

		movimentacao2.setConta(conta);
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		//Preciso persistir as categorias e torná-las managed primeiro.
		em.persist(categoria1);
		em.persist(categoria2);

		//Persisto movimentações.
		em.persist(movimentacao1);
		em.persist(movimentacao2);
		
		//Exemplo de update
//		Movimentacao m = em.find(Movimentacao.class, 4);
//		m.setValor(new BigDecimal("580.60"));
		
		em.getTransaction().commit();
		em.close();
	}
	
}
