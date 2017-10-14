package financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;

/*
 * Formas de correção:
 * em.close() para depois do for.
 * Colocar FetchType.EAGER no relacionamento: @OneToMany(mappedBy = "conta", fetch = FetchType.EAGER).
 * Substituir o em.find() por uma query com join.
 */
public class TestaLazyInitializationException {

	public static void main(String[] args) {

        EntityManager em = Connection.geEntityManager(Connection.FINANCAS);

        Conta conta = em.find(Conta.class, 2);

        List<Movimentacao> movimentacoes = conta.getMovimentacoes();

        em.close();

        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Movimentação: " + movimentacao.getDescricao());
        }

    }
	
}
