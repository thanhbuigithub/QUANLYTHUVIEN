package modules.dao;

import controller.GlobalDataSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

public abstract class AbstractDAO<T> implements Serializable{
    protected Session session = GlobalDataSession.getSession();
    protected Class clazz;

    private final String INSERT = "insert";
    private final String REMOVE = "remove";
    private final String UPDATE = "update";

    public AbstractDAO(Class clazz) {
        this.clazz = clazz;
    }

    protected <T> boolean Do (String action, T oj){
        try {
            session = GlobalDataSession.getSession();
            session.getTransaction().begin();
            switch (action){
                case INSERT:
                    session.save(oj);
                    break;
                case REMOVE:
                    session.remove(oj);
                    break;
                case UPDATE:
                    session.update(oj);
                    break;
            }
            return true;
        } catch (HibernateException hibernateEx){
            session.getTransaction().rollback();
            hibernateEx.printStackTrace();
            return false;
        } finally {
            session.getTransaction().commit();
        }
    }

    public <T> boolean insert (T oj){
        return Do(INSERT,oj);
    }

    public <T> boolean remove (T oj){
        return Do(REMOVE,oj);
    }

    public <T> boolean update (T oj){
        return Do(UPDATE,oj);
    }

    public  <T> List<T> getAll() {
        try {
            session = GlobalDataSession.getSession();
            session.getTransaction().begin();
            String sql = "from " + clazz.getName();
            Query query = session.createQuery(sql);
            return query.getResultList();
        } catch (HibernateException hibernateEx){
            hibernateEx.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        return null;
    }

    private  <T> List<T> getById(Integer id) {
        try {
            session = GlobalDataSession.getSession();
            session.getTransaction().begin();
            String sql = "from " + clazz.getName() + " table where table.id = :id";
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (HibernateException hibernateEx){
            hibernateEx.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        return null;
    }
}
