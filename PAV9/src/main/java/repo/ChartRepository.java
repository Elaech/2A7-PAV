package repo;


import entity.ChartEntity;

/**
 * Repository ul pentru chart nu are nevoie de alte metode inafara de cele implementate in Abstract Repository
 * El nu poate fi decat preluat,sters sau fi vazut dimensiunea lui
 */
public class ChartRepository extends AbstractRepository{

    public ChartRepository(){
        super(new ChartEntity());
    }
}
