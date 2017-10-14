package financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * @Id// Chave prim�ria 
	// Gera a chave prim�ria automaticamente. A strategy define se � por auto-incremento ou sequence.
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
 * @author MichaelSantos
 *
 */

@Entity // Representa uma tabela/rela��o/entidade no BD
public class Conta {
	
	@Id// Chave prim�ria 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	//mappedBy: Indica que j� houve o mapeamento no atributo conta 
	//na classe Movimentacao. Isso evita que uma nova tabela de 
	//relacionamento seja criada.
	//Apenas como um espelho (bidirecional) evitando que 
	//crie duas vezes o relacionamento no banco.
	/*
	 * fetch=FetchType.EAGER
	 * Permite que qualquer consulta em Conta 
	 * retorne todas as movimenta��es desta.
	 * Caso contr�rio, ao tentar acessar movimenta��es de conta, 
	 * o java lancar� uma TestaLazyInitializationException
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
