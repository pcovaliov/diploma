package md.usm.tm.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by pcovaliov on 5/29/2017.
 */
public abstract class AbstractGenericDao<T extends Serializable, I extends Serializable> {

    private final Class<T> clazz;

    protected AbstractGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected abstract EntityManager getEntityManager();

    public T getById(final I id) {
        return getEntityManager().find(clazz, id);
    }

    public List<T> getAll() {
        final CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = cb.createQuery(clazz);
        final Root<T> rootEntity = cq.from(clazz);
        final CriteriaQuery<T> all = cq.select(rootEntity);
        final TypedQuery<T> allQuery = getEntityManager().createQuery(all);
        return allQuery.getResultList();
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void delete(final T entity) {
        getEntityManager().remove(entity);
    }

    public T merge(final T entity) {
        return getEntityManager().merge(entity);
    }
}
