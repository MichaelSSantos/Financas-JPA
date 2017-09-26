package financas.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * @Temporal: 
 * 	Define o parâmetro de precisão desejado (TemporalType). Temos 3 opções:
		DATE: somente a data, sem a hora;
		TIME: somente a hora, sem data;
		TIMESTAMP: tanto data quanto hora.
 * */
@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	private String descricao;
	
	//Uma conta tem muitas movimentações 
	//Movimentacao n .. 1 Conta
	@ManyToOne
	private Conta conta;
	
	//Uma movimentação pode ter muitas categorias
	//E uma categoria pode estar presente em muitas moviemntações
	//n .. n
	@ManyToMany
	private List<Categoria> categorias;
	
	public Movimentacao() {
		this.categorias = new ArrayList<Categoria>();
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", valor=" + valor + ", tipo=" + tipo + ", data=" + data + ", descricao="
				+ descricao + ", conta=" + conta + ", categorias=" + categorias + "]";
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}
	

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
