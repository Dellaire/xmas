package net.sprd.xmas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.sprd.xmas.ui.ImageEntity;

@Repository
public interface ImageRepository extends MongoRepository<ImageEntity, String> {

}
