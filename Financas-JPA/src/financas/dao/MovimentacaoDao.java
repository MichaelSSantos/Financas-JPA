package financas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import financas.modelo.Categoria;
import financas.modelo.Movimentacao;

public class MovimentacaoDao {

	public List<Movimentacao> findByCategoria(Categoria categoria){
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		/*A jun��o est� sendo feita com o atributo categoria, que tem a anota��o 
		 * @ManyToMany. A JPA ir� gerar o SQL correspondente e buscar o @Id de categoria
		 * para associar ao SQL.
		 * */
		
		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);
		
		List<Movimentacao> movimentacoes = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movimentacoes;
	}
	
	public static void main(String[] args) {
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		for (Movimentacao movimentacao : movimentacaoDao.findByCategoria(categoria)) {
			
			//A JPA faz o join em todas as tabelas de relacionamento.
			//Veja que n�o especifiquei nada em rela��o � conta e a JPA me retornou essa informa��o.
			System.out.printf("Valor: %.2f - Categoria: %s \n", movimentacao.getValor(), movimentacao.getConta().getBanco());
			
		}
		
	}
	
}
