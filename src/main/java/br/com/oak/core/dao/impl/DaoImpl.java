package br.com.oak.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.oak.core.dao.Dao;
import br.com.oak.core.entidade.Paginacao;

@Repository("dao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class DaoImpl<T> implements Dao<T> {

	private static final long serialVersionUID = 1610377005116940189L;

	private Class<T> persistentClass;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private String databaseRole;

	@Autowired
	private Boolean databaseRoleEnabled;

	public final Class<T> getPersistentClass() {
		if (persistentClass == null) {
			throw new RuntimeException(
					"E necessario invocar o metodo setPersistentClass(Class<T> clazz)");
		}
		return persistentClass;
	}

	@Override
	public final void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Override
	public void save(T obj) {
		this.setRole();
		this.entityManager.persist(obj);
	}

	@Override
	public void update(T obj) {
		this.setRole();
		this.entityManager.merge(obj);
	}

	@Override
	public void delete(Serializable id) {
		this.setRole();
		this.entityManager.remove(findById(id));
	}

	@Override
	public void refresh(T entity) {
		this.entityManager.refresh(entity);
	}

	@Override
	public T findById(Serializable id) {
		return this.entityManager.find(getPersistentClass(), id);
	}

	@Override
	public Object findSingleResult(final String queryStr, Object... params) {
		return findSingleResult(queryStr, null, params);
	}

	@Override
	public Object findSingleResult(final String queryStr,
			final Paginacao paginacao, Object... params) {
		return createQuery(queryStr, paginacao, params).getSingleResult();
	}

	@Override
	public List<?> find(final String queryStr, Object... params) {
		return find(queryStr, null, params);
	}

	@Override
	public List<?> find(final String queryStr, final Paginacao paginacao,
			Object... params) {
		return createQuery(queryStr, paginacao, params).getResultList();
	}

	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(final Paginacao paginacao) {

		final Query query = this.entityManager.createQuery(" FROM "
				+ getPersistentClass().getSimpleName());

		paginar(paginacao, query);

		return query.getResultList();
	}

	@Override
	public List<T> findByNativeQuery(final String sql, Object... params) {
		return findByNativeQuery(sql, null, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNativeQuery(final String sql,
			final Paginacao paginacao, Object... params) {

		final Query query = this.entityManager.createNativeQuery(sql);

		setQueryParams(query, params);

		paginar(paginacao, query);

		return query.getResultList();
	}

	private Query createQuery(final String queryStr, final Paginacao paginacao,
			Object... params) {

		final Query query = this.entityManager.createQuery(queryStr);

		setQueryParams(query, params);

		paginar(paginacao, query);

		return query;
	}

	private void paginar(final Paginacao paginacao, final Query query) {

		if (paginacao != null) {

			if (paginacao.getPosicao() != null) {
				query.setFirstResult(paginacao.getPosicao());
			}

			if (paginacao.getLimite() != null) {
				query.setMaxResults(paginacao.getLimite());
			}
		}
	}

	private void setQueryParams(final Query query, Object... params) {

		if (params != null && params.length > 0) {

			int i = 0;

			for (final Object param : params) {

				if (param != null) {

					if ((param instanceof String && ((String) param).trim()
							.isEmpty())) {
						continue;
					}
					i++;
					query.setParameter(i, param);
				}
			}
		}
	}

	private void setRole() {
		if (this.databaseRoleEnabled) {
			this.entityManager.createNativeQuery(databaseRole).executeUpdate();
		}
	}
}