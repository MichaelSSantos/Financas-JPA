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

public class PopulaMovimentacao {

	public static void main(String[] args) {
		
//		Categoria categoria1 = new Categoria("13 Salário");
//		Categoria categoria2 = new Categoria("Salário mensal");
	
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		
		
		Conta conta = new Conta();
		conta.setId(1);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 15);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(calendar);
		movimentacao1.setDescricao("Viagem ao Peru");
		movimentacao1.setValor(new BigDecimal("1200.0"));
		movimentacao1.setTipo(TipoMovimentacao.SAIDA);
		movimentacao1.setCategorias(Arrays.asList(categoria));
		movimentacao1.setConta(conta);
		
//		Movimentacao movimentacao2 = new Movimentacao();
//		movimentacao2.setData(Calendar.getInstance());
//		movimentacao2.setDescricao("Primeira parcela do 13 salário");
//		movimentacao2.setValor(new BigDecimal("2500.0"));
//		movimentacao2.setTipo(TipoMovimentacao.SAIDA);
//		movimentacao2.setCategorias(Arrays.asList(categoria1));
//		movimentacao2.setConta(conta);
		
		EntityManager em = Connection.geEntityManager(Connection.FINANCAS);
		em.getTransaction().begin();
		
//		em.persist(categoria1);
//		em.persist(categoria2);

		em.persist(movimentacao1);
//		em.persist(movimentacao2);
		
		em.getTransaction().commit();
		em.close();
	}
	
}
