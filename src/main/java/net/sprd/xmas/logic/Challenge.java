package net.sprd.xmas.logic;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.ByteArrayInputStream;

@Document
public class Challenge {

	@Id
	private String id;
	
	private Double solvencyScore;
	private byte[] fraudSuspectImage;
	private byte[] solvencyScoreImage;

	public Double getSolvencyScore() {
		return solvencyScore;
	}

	public Challenge setSolvencyScore(Double solvencyScore) {
		this.solvencyScore = solvencyScore;
		return this;
	}

	public String getId() {
		return id;
	}

	public Challenge setId(String id) {
		this.id = id;
		return this;
	}

	public byte[] getFraudSuspectImage() {
		return fraudSuspectImage;
	}

	public Challenge setFraudSuspectImage(byte[] content) {
		this.fraudSuspectImage = content;
		return this;
	}

	public Image getFraudSuspectAsImage() {

		StreamResource resource = new StreamResource(this.id, () -> new ByteArrayInputStream(this.fraudSuspectImage));
		Image image = new Image(resource, "error");

		return image;
	}

	public byte[] getSolvencyScoreImage() {
		return solvencyScoreImage;
	}

	public Challenge setSolvencyScoreImage(byte[] bonityScore) {
		this.solvencyScoreImage = bonityScore;
		return this;
	}

	public Image getSolvencyScoreAsImage() {

		StreamResource resource = new StreamResource(this.id, () -> new ByteArrayInputStream(this.fraudSuspectImage));
		Image image = new Image(resource, "error");

		return image;
	}
}
