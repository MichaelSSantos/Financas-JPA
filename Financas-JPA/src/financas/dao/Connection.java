package financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
	
	public static final String FINANCAS = "mysql_financas"; 
	
	private static EntityManagerFactory emf;
	
	public static EntityManager geEntityManager(String persistenceUnitName) {
		//Obtém as configurações da unidade de persistência definidas no persistence.xml
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		//Abre conexão com o banco de dados
		return emf.createEntityManager();
	}
	
}
