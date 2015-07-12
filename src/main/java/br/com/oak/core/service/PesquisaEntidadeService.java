package br.com.oak.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Entidade;

public abstract class PesquisaEntidadeService<E extends Entidade> {

	protected abstract OakDao<E> getDao();

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public E obter(Serializable id) {
		return getDao().obter(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<E> listar() {
		return getDao().listar();
	}
}