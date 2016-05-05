/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 *
 * @author maluleque
 * @param <C> representa uma entidade qualquer
 */
public class GenericDao<C extends Serializable> {

    private final Session session;
    private final Class<C> persistentClass;
    private static GenericDao instancia;

    public GenericDao() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.persistentClass = (Class<C>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public static GenericDao getInstancia(){
        if(instancia==null){
            instancia = new GenericDao();
        }
     return instancia;
    }

    public Session getSession() {
        return session;
    }

    protected void guardar(C entity) {
        try {
            getSession().getTransaction().begin();
            getSession().save(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
        } finally {
            getSession().close();
        }
    }

    protected void actualizar(C entity) {
        try {
            getSession().getTransaction().begin();
            getSession().update(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
        } finally {
            close();
        }
    }

    protected void apagar(C entity) {
        try {
            getSession().getTransaction().begin();
            getSession().delete(entity);
            getSession().getTransaction().commit();
        } catch (Throwable t) {
            getSession().getTransaction().rollback();
        } finally {
            close();
        }
    }

    public List<C> buscaTodos()  {
        return getSession().createCriteria(persistentClass).list();
    }

    public C procuraPorNome(String nome) {
        return (C) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq("nome", nome).ignoreCase()).uniqueResult();
    }

    public C procuraPorId(Long id) {
        return (C) getSession().createCriteria(persistentClass)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    private void close() {
        if (getSession() != null && getSession().isOpen()) {
            getSession().close();
        }
    }
}
