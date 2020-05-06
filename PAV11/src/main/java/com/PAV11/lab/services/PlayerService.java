package com.PAV11.lab.services;

import com.PAV11.lab.exceptions.MyException;
import com.PAV11.lab.models.Player;
import com.PAV11.lab.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    public List<Player> getAllPlayers(){
        List<Player> players = repository.findAll();
        if(players.size()>0){
            return players;
        }
        return new ArrayList<>();
    }

    public Player createPlayer(Player player){
        player.setId(UUID.randomUUID());
        player = repository.save(player);
        return player;
    }

    public String deletePlayer(UUID playerID){
        if(repository.existsById(playerID)){
            repository.deleteById(playerID);
            return "Success";
        }
        else throw new MyException("Game not found");
    }

    public String updatePlayer(Player player){
        try {
            Player repPlayer = repository.findById(player.getId()).get();
            repository.save(player);
            return "Player Updated";
        } catch (NoSuchElementException e){
            throw new MyException("Game not found");
        }
    }
}
