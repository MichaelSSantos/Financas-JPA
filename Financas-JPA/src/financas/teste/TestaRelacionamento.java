package financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;
import financas.modelo.TipoMovimentacao;

public class TestaRelacionamento {

	public static void main(String[] args) {
		
//		Conta conta = new Conta();
//		conta.setAgencia("185");
//		conta.setBanco("Banco do Brasil");
//		conta.setNumero("005289");
//		conta.setTitular("Macedo Nunes Oliveira");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setTipo(TipoMovimentacao.ENTRADA);
		movimentacao.setDescricao("Salário");
		movimentacao.setValor(new BigDecimal("2500.0"));
		
//		movimentacao.setConta(conta);
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
//		em.persist(conta);
		
		Conta conta = em.find(Conta.class, 2);
		movimentacao.setConta(conta);
		em.persist(movimentacao);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
