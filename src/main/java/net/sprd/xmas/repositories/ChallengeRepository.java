package net.sprd.xmas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.sprd.xmas.logic.ChallengeEntity;

@Repository
public interface ChallengeRepository extends MongoRepository<ChallengeEntity, String> {

}
