package br.com.oak.core.entidade;

import java.io.Serializable;

public class ParametroDeOrdenacao implements Serializable {

	private static final long serialVersionUID = 6778814391774069906L;

	private String campo;

	private String ordenacao;

	private boolean castLong = false;

	public ParametroDeOrdenacao(String campo, String ordenacao) {
		this.campo = campo;
		this.ordenacao = ordenacao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(String ordenacao) {
		this.ordenacao = ordenacao;
	}

	public boolean isCastLong() {
		return castLong;
	}

	public void setCastLong(boolean castLong) {
		this.castLong = castLong;
	}
}
