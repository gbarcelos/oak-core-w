package br.com.oak.core.enums;

import org.apache.commons.lang.StringUtils;

public enum StatusRegistroAtivo {

	ATIVO("S"),

	INATIVO("N");

	private StatusRegistroAtivo(String valor) {
		this.valor = valor;
	}

	private String valor;

	public String getValor() {
		return valor;
	}

	public static StatusRegistroAtivo recuperaStatusRegistroAtivo(Object codigo) {
		for (StatusRegistroAtivo s : StatusRegistroAtivo.values()) {
			if (s.getValor().equals(codigo)) {
				return s;
			}
		}
		return null;
	}

	public static String recuperaDescricaoPorCodigo(String codigo) {
		if (StringUtils.isNotBlank(codigo)) {
			if (StatusRegistroAtivo.ATIVO.getValor().equalsIgnoreCase(codigo)) {
				return StatusRegistroAtivo.ATIVO.toString();
			} else {
				return StatusRegistroAtivo.INATIVO.toString();
			}
		}
		return null;
	}
}