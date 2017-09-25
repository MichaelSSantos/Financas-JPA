package financas.teste;

import financas.dao.ContaDao;
import financas.modelo.Conta;

public class TesteBuscaConta {

	public static void main(String[] args) {
		
		ContaDao contaDao = new ContaDao();
		Conta conta = contaDao.findById(new Integer(1));
		conta.setTitular("Tiririca");
		System.out.println(conta.toString());
		
	}

}
