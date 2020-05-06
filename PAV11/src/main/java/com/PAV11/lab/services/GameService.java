package com.PAV11.lab.services;

import com.PAV11.lab.exceptions.MyException;
import com.PAV11.lab.models.Game;
import com.PAV11.lab.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameRepository repository;

    public List<Game> getAllGames() {
        List<Game> games = repository.findAll();
        if (games.size() > 0) {
            return games;
        } else {
            return new ArrayList<>();
        }
    }
    public Game createGame(Game game){
        game.setId(UUID.randomUUID());
        game = repository.save(game);
        return game;
    }
    public String deleteGame(UUID gameID){
        if(repository.existsById(gameID)){
            repository.deleteById(gameID);
            return "Success";
        }
        else throw new MyException("Game not found");
    }
}
