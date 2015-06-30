package br.com.oak.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import net.vidageek.mirror.dsl.Mirror;

public class ReflectionsUtil {

	private ReflectionsUtil() {
	}

	public static Class<?> getTipoGenerico(Class<?> classe) {
		ParameterizedType tipo = (ParameterizedType) classe.getGenericSuperclass();
		Type[] parametro = tipo.getActualTypeArguments();
		Class<?> tipoGenerico = null;

		if ((parametro != null) && (0 < parametro.length)) {
			tipoGenerico = (Class<?>) parametro[0];
		}

		return tipoGenerico;
	}

	public static <A extends Annotation> A getAnotacaoMetodo(Class<?> classe, Class<A> anotacao, String metodo) {
		return new Mirror().on(classe).reflect().annotation(anotacao).atMethod(metodo).withoutArgs();
	}

	public static <T> T newInstance(Class<T> classe) {
		try {
			return classe.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Erro ao criar instência da classe: " + classe.getCanonicalName());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Erro ao criar instência da classe: " + classe.getCanonicalName());
		}
	}
}
