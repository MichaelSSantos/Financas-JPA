package financas.teste;

import financas.dao.ContaDao;
import financas.modelo.Conta;

public class TestaPopulaConta {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		
		conta.setTitular("Joana Lima Almeida");
		conta.setBanco("Banco do Brasil");
		conta.setAgencia("1852");
		conta.setNumero("00547");
		
		Conta conta1 = new Conta();
		
		conta1.setTitular("Júlio Aguiar Rocha");
		conta1.setBanco("Caixa Econômica");
		conta1.setAgencia("245");
		conta1.setNumero("026297");
		
		Conta conta2 = new Conta();
		
		conta2.setTitular("Darcy Luízio Alves");
		conta2.setBanco("Banco Santander");
		conta2.setAgencia("112");
		conta2.setNumero("122548");
		
		ContaDao contaDao = new ContaDao();
		
		contaDao.incluir(conta);
		contaDao.incluir(conta1);
		contaDao.incluir(conta2);
		
	}
	
}
