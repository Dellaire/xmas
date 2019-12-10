package net.sprd.xmas.repositories;

import net.sprd.xmas.logic.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScoreRepository extends MongoRepository<Score, String> {

    List<Score> findAllByOrderByErrorAsc();
}
