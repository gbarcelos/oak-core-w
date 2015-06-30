package br.com.oak.core.dao;

import java.io.Serializable;
import java.util.List;

import br.com.oak.core.entidade.Entidade;

public interface OakDao<E extends Entidade> extends Dao<E> {

	public void incluir(E entidade);

	public void alterar(E entidade);

	public void excluir(E entidade);

	public E obter(Serializable id);

	public List<E> listar();
}