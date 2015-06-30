package br.com.oak.core.dao;

import java.io.Serializable;
import java.util.List;

import br.com.oak.core.entidade.Paginacao;

public interface Dao<T> extends Serializable {

	void setPersistentClass(Class<T> persistentClass);

	void save(T obj);

	void update(T obj);

	void delete(Serializable id);

	void refresh(T entity);

	T findById(Serializable id);

	Object findSingleResult(String queryStr, Object... params);

	Object findSingleResult(String queryStr, Paginacao paginacao, Object... params);

	List<?> find(String queryStr, Object... params);

	List<?> find(String queryStr, Paginacao paginacao, Object... params);

	List<T> findAll();

	List<T> findAll(Paginacao paginacao);

	List<T> findByNativeQuery(String sql, Object... params);

	List<T> findByNativeQuery(String sql, Paginacao paginacao, Object... params);
}