package md.usm.tm.dao;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by pcovaliov on 5/29/2017.
 */
public abstract class AbstractGenericDao<T extends Serializable, I extends Serializable> {

    private final Class<T> clazz;

    protected AbstractGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected abstract Session getCurrentSession();

    @Transactional
    public T getById(final I id) {
        return getCurrentSession().find(clazz, id);
    }

    public T persist(final T entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    public T update(final T entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    public void flush() {
        getCurrentSession().flush();
    }

}
