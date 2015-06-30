package br.com.oak.core.exception;

import java.io.Serializable;

import br.com.oak.core.enums.CamposEnum;

public class Erro implements Serializable {

	private static final long serialVersionUID = 772924219648074112L;

	private final String codigo;

	private final CamposEnum campo;

	private Object[] parametros;

	public Erro(final String codigo) {
		this.codigo = codigo;
		this.campo = null;
		this.parametros = null;
	}

	public Erro(final String codigo, final CamposEnum campo) {
		this.codigo = codigo;
		this.campo = campo;
		this.parametros = new Object[1];
		this.parametros[0] = campo.getDescricao();
	}

	public Erro(final String codigo, final CamposEnum campo,
			Object... parametros) {
		this.codigo = codigo;
		this.campo = campo;
		popularParametros(parametros);
	}

	public Erro(final String codigo, Object... parametros) {

		this.codigo = codigo;

		this.campo = null;

		popularParametros(parametros);
	}

	private void popularParametros(Object... parametros) {

		if (parametros != null && parametros.length > 0) {

			int qtdParam = parametros.length;

			if (this.campo != null) {
				qtdParam++;
			}

			this.parametros = new Object[qtdParam];

			if (this.campo != null) {

				this.parametros[0] = this.campo.getDescricao();

				for (int i = 1; i < this.parametros.length; i++) {
					this.parametros[i] = parametros[i - 1];
				}

			} else {
				for (int i = 0; i < this.parametros.length; i++) {
					this.parametros[i] = parametros[i];
				}
			}

		} else if (this.campo != null) {

			this.parametros = new Object[1];
			this.parametros[0] = this.campo.getDescricao();

		} else {
			this.parametros = null;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public CamposEnum getCampo() {
		return campo;
	}

	public Object[] getParametros() {
		return parametros;
	}
}