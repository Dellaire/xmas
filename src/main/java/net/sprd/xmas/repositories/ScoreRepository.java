package net.sprd.xmas.repositories;

import net.sprd.xmas.logic.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreRepository extends MongoRepository<Score, String> {
}
