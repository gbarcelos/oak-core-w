package br.com.oak.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oak.core.entidade.Entidade;
import br.com.oak.core.exception.RegistroNaoExisteException;

public abstract class EntidadeService<E extends Entidade> extends PesquisaEntidadeService<E> {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void incluir(E entidade) {
		getDao().incluir(entidade);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void alterar(E entidade) {
		getDao().alterar(entidade);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void excluir(Serializable id) {
		E entidade = obter(id);

		if (entidade == null) {
			throw new RegistroNaoExisteException();
		}

		getDao().excluir(entidade);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void excluirTodos(List<E> entidades) {
		if (entidades != null && !entidades.isEmpty()) {
			for (E entidade : entidades) {
				excluir(entidade.getId());
			}
		}
	}
}