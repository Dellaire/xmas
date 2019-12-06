package net.sprd.xmas.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChallengeEntity {

	@Id
	private String id;
	
	private Double score;
	private byte[] fraudSuspect;
	private byte[] bonityScore;

	public Double getScore() {
		return score;
	}

	public ChallengeEntity setScore(Double score) {
		this.score = score;
		return this;
	}

	public String getId() {
		return id;
	}

	public ChallengeEntity setId(String id) {
		this.id = id;
		return this;
	}

	public byte[] getFraudSuspect() {
		return fraudSuspect;
	}

	public ChallengeEntity setFraudSuspect(byte[] content) {
		this.fraudSuspect = content;
		return this;
	}

	public byte[] getBonityScore() {
		return bonityScore;
	}

	public ChallengeEntity setBonityScore(byte[] bonityScore) {
		this.bonityScore = bonityScore;
		return this;
	}
}
