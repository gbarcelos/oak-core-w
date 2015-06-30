package br.com.oak.core.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static String somenteNumero(final String texto){

		String textoRetorno = "";

		if (StringUtils.isNotBlank(texto)){
			textoRetorno = texto.replaceAll("[^0-9]", " ");
		}
		return textoRetorno;
	}
	
	public static String retirarEspacoExcedente(final String texto){

		String textoRetorno = "";

		if (StringUtils.isNotBlank(texto)){
			textoRetorno = texto.replaceAll("\\s\\s++", " ").trim();
		}
		return textoRetorno;
	}
	
	public static String retiraAcentuacao(String message) {
		String temp = Normalizer.normalize(message, Form.NFKD);
		return temp.replaceAll("[^\\p{ASCII}]", "");
	}

	public static String retiraCaracteresEspeciais(final String texto){
		
		String textoRetorno = "";
		
		if (StringUtils.isNotBlank(texto)){
			textoRetorno = texto.replaceAll("[-+=*&;:%^$#§@´¨!?°º¹²³£¢¬ª~_]", " ");
			textoRetorno = textoRetorno.replaceAll("['\"]", " ");
			textoRetorno = textoRetorno.replaceAll("[<>\\[\\]()\\{\\}]", " ");
			textoRetorno = textoRetorno.replaceAll("['\\\\.,()|/]", " ");
		    textoRetorno = textoRetorno.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		    textoRetorno = retirarEspacoExcedente(textoRetorno);
		}
		return textoRetorno;
	}
	
	public static String retiraNumero(final String texto){
		
		String textoRetorno = "";
		
		if (StringUtils.isNotBlank(texto)){
			textoRetorno = texto.replaceAll("[0-9]", " ");
			textoRetorno = retirarEspacoExcedente(textoRetorno);
		}
		return textoRetorno;
	}
	
	public static String trocaString(String text, String replaceOut,
			String replaceIn) {
		StringBuffer texto = new StringBuffer(text);
		Collection<Integer> indices = new ArrayList<Integer>();
		Integer a = -1;
		do {
			a = texto.indexOf(replaceOut, a + 1);
			if (a != -1) {
				indices.add(a);
			}
		} while (a != -1);
		Integer acum = 0;
		for (Integer indice : indices) {
			int i = indice + acum;
			texto.replace(i, i + replaceOut.length(), replaceIn);
			acum += (replaceOut.length() - replaceIn.length());
		}
		return texto.toString();
	}
}
