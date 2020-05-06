package com.PAV11.lab.repository;

import com.PAV11.lab.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
}
