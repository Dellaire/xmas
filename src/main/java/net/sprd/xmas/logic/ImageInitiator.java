package net.sprd.xmas.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import net.sprd.xmas.repositories.ImageRepository;
import net.sprd.xmas.ui.ImageEntity;

@Component
@Profile("import")
public class ImageInitiator implements CommandLineRunner {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public void run(String... args) throws Exception {

		this.saveImage("image1.jpg");
		this.saveImage("image2.jpg");
	}

	private void saveImage(String imageName) throws IOException {

		String imagePath = this.getClass().getClassLoader().getResource(imageName).getFile();
		if (imagePath.startsWith("/")) {
			imagePath = imagePath.substring(1);
		}
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		this.imageRepository.save(new ImageEntity().setId(imageName).setContent(imageBytes));
	}
}
