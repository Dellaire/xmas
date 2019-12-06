package net.sprd.xmas.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import net.sprd.xmas.logic.ChallengeEntity;
import net.sprd.xmas.repositories.ChallengeRepository;

@Route("xmas")
public class Playground extends VerticalLayout {

	private final ChallengeRepository challengeRepository;
	
	private ChallengeEntity challengeEntity;
	private Double guess;
	
	public Playground(ChallengeRepository challengeRepository) throws IOException {
		
		this.challengeRepository = challengeRepository;

		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.setHeightFull();
		
		this.displayFraudSuspect();
	}
	
	private void displayFraudSuspect() {
		
		Label label = new Label("");
		label.setHeight("25%");
		
		TextField textField = new TextField("Bonity Score");
		
		Button button = new Button("Submit");
		button.addClickListener(event -> this.guess = Double.parseDouble(textField.getValue()));
		button.addClickListener(event -> this.displayBonityScore());
		
		this.removeAll();
		this.add(label);
		try {
			this.add(this.readChallenge("image1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(textField);
		this.add(button);
	}
	
	private void displayBonityScore() {
		
		Label label = new Label("");
		label.setHeight("30%");
		
		Button button = new Button("Restart");
		button.addClickListener(event -> this.displayFraudSuspect());
		
		this.removeAll();
		this.add(label);
		this.add(new Label(this.guess));
		this.add(button);
	}
	
	private Image readChallenge(String challengeName) throws IOException {
		
		ChallengeEntity imageEntity = this.challengeRepository.findById(challengeName).get();
		byte[] imageBytes = imageEntity.getFraudSuspect();
		StreamResource resource = new StreamResource(challengeName, () -> new ByteArrayInputStream(imageBytes));
		Image image = new Image(resource, "error");
		
		return image;
	}
}
