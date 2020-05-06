package com.PAV11.lab.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID player1ID;
    private UUID player2ID;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayer1ID() {
        return player1ID;
    }

    public void setPlayer1ID(UUID player1ID) {
        this.player1ID = player1ID;
    }

    public UUID getPlayer2ID() {
        return player2ID;
    }

    public void setPlayer2ID(UUID player2ID) {
        this.player2ID = player2ID;
    }
}
