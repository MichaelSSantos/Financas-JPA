package financas.teste;

import javax.persistence.EntityManager;

import financas.dao.Connection;
import financas.modelo.Cliente;
import financas.modelo.Conta;

public class TestaContaCliente {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
		conta.setId(3);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Gomez Perez");
		cliente.setEndereco("Rua Otto, 153");
		cliente.setProfissao("Advogado");
		cliente.setConta(conta);
		
		EntityManager em = Connection.geEntityManager("mysql_financas");
		em.getTransaction().begin();
		
		em.persist(cliente);
		
		em.getTransaction().commit();
		em.close();
		
	}

}
