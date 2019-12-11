package net.sprd.xmas.repositories;

import net.sprd.xmas.logic.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends MongoRepository<Score, String> {

    List<Score> findAllByOrderByErrorAsc();

    Boolean existsByName(String name);
}
