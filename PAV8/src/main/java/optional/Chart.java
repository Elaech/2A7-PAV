package optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Reprezinta un chart al artistilor
 * Acesta poate fi facut local si lucrat cu el cu valori ce nu apartin DB
 * sau poate fi folosit pentru a il umple cu datele actuale din db
 */
public class Chart {
    List<Artist> artistList= new ArrayList<>();

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void addArtist(Artist artist){
        artistList.add(artist);
    }

    public void sortChart(){
        artistList.sort(((o1, o2) -> {
            return o2.getPopularity()-o1.getPopularity();
        }));
    }

    public Artist getArtistAtPosition(int index){
        return artistList.get(index);
    }

    /**
     * afiseaza topul artistilor detinuti in lista de artisti
     */
    public void printTop(){
        System.out.println("Top Artists in the chart:");
        int index = 0;
        for(Artist artist:artistList){
            index++;
            System.out.println(index+". "+artist.getName()+" "+artist.getPopularity());
        }
    }

    /**
     * Cu ajutorul controllerului chart updateaza informatiile locale si din DB legate de top
     * sterge continutul actual al listei de artisti si adauga artistii din baza de date din top
     */
    public void updateChartToLive(){
        ChartController chartController = new ChartController();
        chartController.updateChart();
        artistList.removeAll(artistList);
        artistList.addAll(chartController.getChart());
    }
}
