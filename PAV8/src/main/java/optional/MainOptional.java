package optional;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * In clasa main generam cu ajutorul Faker importat de pe github cu frameworkul maven
 * mai multe intrari in tabela artisti si albume
 * (fiecare artist cate un album)
 */
public class MainOptional {
    public static void main(String[] args) {
        //initializare
        Faker faker = new Faker();
        ArtistController artistController = new ArtistController();
        AlbumController albumController = new AlbumController();
        List<String> names = new ArrayList<>();

        try {
            //creare si inserare in baza de date a artistilor, retinem numele intr-un array separat
            for (int i = 0; i < 1000; i++) {
                String name = faker.name().fullName();
                names.add(name);
                artistController.create(new Artist(name, faker.country().name()));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //pentru numele tinute minte obtinem ID-urile intr-un nou array(ID-urile sunt generate de catre sv de BD deci trebuie sa le obtinem)
        List<Integer> artistIds = new ArrayList<>();
        try {
            for (String name : names) {
                artistIds.add(artistController.findByName(name));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //pentru fiecare ID generam un album nou cu nume random
        try {
            for (Integer artistID : artistIds) {
                albumController.create(new Album(faker.funnyName().name(), (int)(Math.random()*120+1900), artistID));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //cream si dam fetch la datele live din baza de date din chart
        Chart chart = new Chart();
        chart.updateChartToLive();
        //afisam datele obtinute anterior, mai exact topul artistilor
        chart.printTop();
    }
}