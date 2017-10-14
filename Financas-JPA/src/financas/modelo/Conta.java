package financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * @Id// Chave primária 
	// Gera a chave primária automaticamente. A strategy define se é por auto-incremento ou sequence.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
 * @author MichaelSantos
 *
 */

@Entity // Representa uma tabela/relação/entidade no BD
public class Conta {
	
	@Id// Chave primária 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	//mappedBy: Indica que já houve o mapeamento no atributo conta 
	//na classe Movimentacao. Isso evita que uma nova tabela de 
	//relacionamento seja criada.
	//Apenas como um espelho (bidirecional) evitando que 
	//crie duas vezes o relacionamento no banco.
	/*
	 * fetch=FetchType.EAGER
	 * Permite que qualquer consulta em Conta 
	 * retorne todas as movimentações desta.
	 * Caso contrário, ao tentar acessar movimentações de conta, 
	 * o java lancará uma TestaLazyInitializationException
	 */
	@OneToMany(mappedBy="conta", fetch=FetchType.EAGER)
	private List<Movimentacao> movimentacoes;
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", titular=" + titular + ", numero=" + numero + ", banco=" + banco + ", agencia="
				+ agencia + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
}
