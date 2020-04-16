package repo;

import entity.ArtistsEntity;
import entity.ChartEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository-ul de artisti ce are in plus fata de abstract repository:
 * metoda de salvare a unui artist
 * metoda de gasire a unui artist dupa nume
 * metoda ce returneaza lista de artisti dupa nume
 */
public class ArtistRepository extends AbstractRepository{

    public ArtistRepository(){
        super(new ArtistsEntity());
    }
    public void create(String Name, String Country){

        entityManager.getTransaction().begin();
        ArtistsEntity artistsEntity = new ArtistsEntity();
        artistsEntity.setCountry(Country);
        artistsEntity.setName(Name);

        entityManager.persist(artistsEntity);
        entityManager.getTransaction().commit();
    }

    public ArtistsEntity findById(long id){

        entityManager.getTransaction().begin();
        ArtistsEntity ae = entityManager.find(ArtistsEntity.class,id);
        entityManager.getTransaction().commit();
        return ae;
    }

    public List<ArtistsEntity> findByName(String Name){

        entityManager.getTransaction().begin();
        List<ArtistsEntity> artistsEntities = entityManager.createNamedQuery("findByName")
                .setParameter("artistName",Name)
                .getResultList();
        entityManager.getTransaction().commit();
        return artistsEntities;
    }


}
