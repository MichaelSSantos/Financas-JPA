package financas.teste;

import financas.dao.MovimentacaoDao;
import financas.modelo.Conta;
import financas.modelo.Movimentacao;

public class TestaMovimentacaoConta {

	public static void main(String[] args) {
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
		
		Movimentacao movimentacao = movimentacaoDao.findById(3);
		Conta conta = movimentacao.getConta();
		
		System.out.println(conta.getMovimentacoes().size());
		
	}
	
}
