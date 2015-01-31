package org.ngo.think.dm.persistence.generic.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ngo.think.dm.persistence.generic.dao.BaseDAO;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDAOImpl<T> implements BaseDAO<T>
{
	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> type;

	@SuppressWarnings({ "rawtypes", "unchecked"})
	public BaseDAOImpl()
	{
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager()
	{
		return entityManager;
	}

	@Transactional
	public T save(final T object) throws Exception
	{
		try
		{
			getEntityManager().persist(object);
			getEntityManager().flush();
		}
		catch (Exception exception)
		{
			throw exception;

		}
		return object;
	}

	@Transactional
	public void delete(final Object id) throws Exception
	{
		try
		{
			getEntityManager().remove(getEntityManager().getReference(type, id));
		}
		catch (Exception exception)
		{

		}
	}

	@Transactional
	public T findByPrimaryKey(final Object id) throws Exception
	{
		T object = null;
		try
		{

			object = getEntityManager().find(type, id);

		}
		catch (Exception exception)
		{
		}
		return object;
	}

	@Transactional
	public T update(final T object) throws Exception
	{
		T updatedObject = null;
		try
		{
			updatedObject = getEntityManager().merge(object);
			getEntityManager().flush();
		}
		catch (Exception exception)
		{
		}
		return updatedObject;
	}


}
