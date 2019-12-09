package net.sprd.xmas.logic;

import net.sprd.xmas.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@Component
@Profile("import")
public class ImageInitiator implements CommandLineRunner {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public void run(String... args) throws Exception {

        this.saveImage("image1.jpg", "image2.jpg");
    }

    private void saveImage(String fraudSuspectFileName, String solvencyScoreFileName) {

        String challengeId = new Random().nextInt(new Long(this.challengeRepository.count() + 1).intValue()) + "";

        byte[] fraudSuspectImage = this.loadImage(fraudSuspectFileName);
        byte[] solvencyScoreImage = this.loadImage(solvencyScoreFileName);

        this.challengeRepository.save(new Challenge().setId(challengeId).setFraudSuspectImage(fraudSuspectImage)
                .setSolvencyScoreImage(solvencyScoreImage).setSolvencyScore(2.8));
    }

    private byte[] loadImage(String pathToImage) {

        String imagePath = this.getClass().getClassLoader().getResource(pathToImage).getFile();
        /*if (imagePath.startsWith("/")) {
            imagePath = imagePath.substring(1);
        }*/

        byte[] imageBytes = {};
        try {
            imageBytes = Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
}
