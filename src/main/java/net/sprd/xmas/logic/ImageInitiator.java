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

        this.saveImage("1_1.PNG", "1_2.PNG", 3.8);
        this.saveImage("2_1.PNG", "2_2.PNG", 6.0);
        this.saveImage("3_1.PNG", "3_2.PNG", 1.3);
        this.saveImage("4_1.PNG", "4_2.PNG", 1.3);
        this.saveImage("5_1.PNG", "5_2.PNG", 1.5);
        this.saveImage("6_1.PNG", "6_2.PNG", 6.0);
        this.saveImage("7_1.PNG", "7_2.PNG", 1.3);
        this.saveImage("8_1.PNG", "8_2.PNG", 1.3);
        this.saveImage("9_1.PNG", "9_2.PNG", 1.0);
        this.saveImage("10_1.PNG", "10_2.PNG", 1.3);
        this.saveImage("11_1.PNG", "11_2.PNG", 6.0);
        this.saveImage("12_1.PNG", "12_2.PNG", 1.3);
        this.saveImage("13_1.PNG", "13_2.PNG", 6.0);
        this.saveImage("14_1.PNG", "14_2.PNG", 6.0);
        this.saveImage("15_1.PNG", "15_2.PNG", 6.0);
        this.saveImage("16_1.PNG", "16_2.PNG", 1.5);
        this.saveImage("17_1.PNG", "17_2.PNG", 1.5);

        System.out.println();
    }

    private void saveImage(String fraudSuspectFileName, String solvencyScoreFileName, Double solvencyScore) {

        byte[] fraudSuspectImage = this.loadImage(fraudSuspectFileName);
        byte[] solvencyScoreImage = this.loadImage(solvencyScoreFileName);

        this.challengeRepository.save(new Challenge().setFraudSuspectImage(fraudSuspectImage)
                .setSolvencyScoreImage(solvencyScoreImage).setSolvencyScore(solvencyScore));
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
