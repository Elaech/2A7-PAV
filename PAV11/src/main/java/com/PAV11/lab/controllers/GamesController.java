package com.PAV11.lab.controllers;

import com.PAV11.lab.models.Game;
import com.PAV11.lab.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GamesController {
    @Autowired
    private GameService service;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames(){
        List<Game> games = service.getAllGames();
        return new ResponseEntity<List<Game>>(games,new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/games")
    public ResponseEntity<Game> createGame(@RequestBody Game game){
        Game game1 = service.createGame(game);
        return new ResponseEntity<Game>(game1,new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/games")
    public ResponseEntity<String> deleteGame(@RequestBody Game game){
        String message = service.deleteGame(game.getId());
        return new ResponseEntity<String>(message,new HttpHeaders(), HttpStatus.OK);
    }
}
