package net.sprd.xmas.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import net.sprd.xmas.repositories.ChallengeRepository;

@Component
@Profile("import")
public class ImageInitiator implements CommandLineRunner {

	@Autowired
	private ChallengeRepository challengeRepository;

	@Override
	public void run(String... args) throws Exception {

		this.saveImage("1","c1Fraud.jpg","c1Solvency.jpg");
		this.saveImage("2","c2Fraud.jpg","c2Solvency.jpg");
	}

	private void saveImage(String challengeId, String fraudSuspectFileName, String solvencyScoreFileName) throws IOException {

		String imagePath = this.getClass().getClassLoader().getResource(challengeId).getFile();
		if (imagePath.startsWith("/")) {
			imagePath = imagePath.substring(1);
		}
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		this.challengeRepository.save(new Challenge().setId(challengeId).setFraudSuspectImage(imageBytes));
	}
}
