package br.com.oak.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Entidade;
import br.com.oak.core.entidade.ExclusaoLogica;
import br.com.oak.core.util.ReflectionsUtil;

public abstract class OakDaoImpl<E extends Entidade> extends DaoImpl<E>
		implements OakDao<E> {

	private static final long serialVersionUID = -4096448385311450325L;

	private HibernateTemplate hibernateTemplate = new HibernateTemplate();

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void incluir(E entidade) {
		save(entidade);
	}

	@Override
	public void alterar(E entidade) {
		update(entidade);
	}

	@Override
	public void excluir(E entidade) {
		if ((entidade instanceof ExclusaoLogica)) {
			((ExclusaoLogica) entidade).setExcluido(true);
			update(entidade);
		} else {
			delete(entidade.getId());
		}
	}

	@Override
	public E obter(Serializable id) {
		return findById(id);
	}

	@Override
	public List<E> listar() {
		return findAll();
	}

	@Autowired
	@SuppressWarnings({ "unchecked", })
	private void configurar() {
		setPersistentClass((Class<E>) ReflectionsUtil
				.getTipoGenerico(getClass()));
		setHibernateTemplate(new HibernateTemplate(
				((Session) entityManager.getDelegate()).getSessionFactory()));
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
}