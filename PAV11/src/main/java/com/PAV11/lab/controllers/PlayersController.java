package com.PAV11.lab.controllers;

import com.PAV11.lab.models.Player;
import com.PAV11.lab.services.GameService;
import com.PAV11.lab.services.PlayerService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PlayersController {
    @Autowired
    private PlayerService service;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = service.getAllPlayers();
        return new ResponseEntity<List<Player>>(players,new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player player1 = service.createPlayer(player);
        return new ResponseEntity<Player>(player1,new HttpHeaders(), HttpStatus.CREATED);
    }
    @PutMapping("/players")
    public ResponseEntity<String> updatePlayer(@RequestBody Player player){
        String message = service.updatePlayer(player);
        return new ResponseEntity<String>(message,new HttpHeaders(), HttpStatus.OK);
    }
    @DeleteMapping("/players")
    public ResponseEntity<String> deletePlayer(@RequestBody Player player){
        String message = service.deletePlayer(player.getId());
        return new ResponseEntity<String>(message,new HttpHeaders(), HttpStatus.OK);
    }
}
