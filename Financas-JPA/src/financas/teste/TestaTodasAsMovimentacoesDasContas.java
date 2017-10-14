package financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import financas.dao.Connection;
import financas.modelo.Conta;

public class TestaTodasAsMovimentacoesDasContas {

	public static void main(String[] args) {
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		String jpqlLazy = "select c from Conta c";//Lazy
//		String jpqlEager = "select c from Conta c join fetch c.movimentacoes";//Eager
		//Eager e distinct para evitar linhas repetidas e com left join para retornar todos os titulares 
		//com ou sem movimentações.
		String jpqlEager = "select distinct c from Conta c left join fetch c.movimentacoes";
		Query query = em.createQuery(jpqlEager);
		
		List<Conta> contas = query.getResultList();
		
		em.close();
		
		for (Conta conta : contas) {
			/*
			 * O que ocorre por debaixo dos panos:
			 * O Hibernate faz um select para retornar todas as contas e para cada conta 
			 * ele retorna todas as movimentações.
			 * Chama-se N+1.
			 * 
			 * Comportamento Lazy: A JPA só busca no banco as listas de um bean, quando se usa o get da lista.
			 * Comportamento Eager: A JPA busca todas as listas independente delas serem usadas.
			 */
			System.out.printf("Titular: %s - Movimentações: %d \n", conta.getTitular(), conta.getMovimentacoes().size());
		}
		
	}
	
}
