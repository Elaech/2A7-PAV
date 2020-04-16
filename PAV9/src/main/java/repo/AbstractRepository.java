package repo;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import entity.ChartEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Abstract repositoriul in care am implementat cateva din metodele specifice CRUD
 * nu am stiut cum sa integrez Generics in cadrul Hibernate Queries asa ca am folosit un switch
 *
 * @param <T> entitatea
 */
public abstract class AbstractRepository<T> {
    protected EntityManagerFactory factory;
    protected EntityManager entityManager;
    private T t;


    public AbstractRepository(T t) {
        factory = PersistenceUtil.getInstance();
        entityManager = factory.createEntityManager();
        this.t = t;
    }

    public long count() {
        entityManager.getTransaction().begin();
        Query query;
        if (t instanceof AlbumsEntity) {
            query = entityManager.createQuery("select count(*) from  AlbumsEntity ");
        } else if (t instanceof ArtistsEntity) {
            query = entityManager.createQuery("select count(*) from  ArtistsEntity ");
        } else {
            query = entityManager.createQuery("select count(*) from  ChartEntity ");
        }
        long count = (long) query.getFirstResult();
        entityManager.getTransaction().commit();
        return count;
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        Query query;
        if (t instanceof AlbumsEntity) {
            query = entityManager.createQuery("delete from AlbumsEntity ");
        } else if (t instanceof ArtistsEntity) {
            query = entityManager.createQuery("delete from ArtistsEntity ");
        } else {
            query = entityManager.createQuery("delete from ChartEntity");
        }
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<T> findAll() {
        entityManager.getTransaction().begin();
        Query query;
        if (t instanceof AlbumsEntity) {
            query = entityManager.createQuery("select A from AlbumsEntity A");
        } else if (t instanceof ArtistsEntity) {
            query = entityManager.createQuery("select A from ArtistsEntity A");
        } else {
            query = entityManager.createQuery("select C from ChartEntity C");
        }
        List<T> tList = query.getResultList();
        entityManager.getTransaction().commit();
        return tList;

    }
}
