package app;

import entity.AlbumsEntity;
import entity.ArtistsEntity;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Clasa in care se regaseste metoda main
 * contine teste pentru metodele specificate in enunt
 */
public class AlbumManager {
    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepository();
        //inserare artist
        artistRepository.create("Reptlia","Wakanda");

        //preluare lista artisti cu numele precizat
        List<ArtistsEntity> artistsEntityList = artistRepository.findByName("Reptlia");
        for(ArtistsEntity a:artistsEntityList){
            System.out.println(a.getName());
        }

        //preluare artist cu id-ul precizat
        ArtistsEntity artistsEntity = artistRepository.findById(9493);
        System.out.println(artistsEntity.getName()+ " " + artistsEntity.getCountry());

        //preluare albume artist
        AlbumRepository albumRepository = new AlbumRepository();
        List<AlbumsEntity> albumsEntityList = albumRepository.findByArtist(artistsEntity);
        for(AlbumsEntity a:albumsEntityList){
            System.out.println(a.getName());
        }

        //folosim metoda findAll din AbstractRepository pentru a lua toti artistii
        artistsEntityList = artistRepository.findAll();
        for(ArtistsEntity a:artistsEntityList){
            System.out.println(a.getName());
        }

        //generam albume pentru artistii curenti
        albumRepository.generateAlbums();
    }
}
