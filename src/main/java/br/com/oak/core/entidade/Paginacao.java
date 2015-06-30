package br.com.oak.core.entidade;

import java.io.Serializable;

public class Paginacao implements Serializable {

	private static final long serialVersionUID = -8685018194904138512L;

	private Integer posicao;

	private Integer limite;

	public Paginacao() {
	}

	public Paginacao(Integer posicao, Integer limite) {
		this.posicao = posicao;
		this.limite = limite;
	}

	public Integer getLimite() {
		return limite;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
}
