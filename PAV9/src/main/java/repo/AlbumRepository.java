package repo;

import com.github.javafaker.Faker;
import entity.AlbumsEntity;
import entity.ArtistsEntity;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository ul de albume ce in plus fata de metodele din abstract repository:
 * are metoda ce gaseste albumele unui artist
 * metoda bonus ce genereaza albume multe cu noul camp genre (valorile generate cu faker)
 */
public class AlbumRepository extends AbstractRepository{


    public AlbumRepository() {
        super(new AlbumsEntity());
    }

    public List<AlbumsEntity> findByArtist(ArtistsEntity ae){
        entityManager.getTransaction().begin();
        List<AlbumsEntity> albumsEntities = entityManager.createNamedQuery("findByArtist")
                .setParameter("artistID",ae.getId())
                .getResultList();
        entityManager.getTransaction().commit();
        return albumsEntities;
    }

    public void generateAlbums(){
        Faker faker = new Faker();
        entityManager.getTransaction().begin();
        List<Long> artistIds = entityManager.createQuery("SELECT A.id FROM ArtistsEntity A").getResultList();
        for(Long id:artistIds){
            AlbumsEntity albumsEntity = new AlbumsEntity();
            albumsEntity.setArtist_id(id);
            albumsEntity.setName(faker.funnyName().name());
            albumsEntity.setReleaseYear((long) faker.number().numberBetween(1900,2020));
            albumsEntity.setGenre(faker.music().genre());
            entityManager.persist(albumsEntity);
        }
        entityManager.getTransaction().commit();
    }

}
