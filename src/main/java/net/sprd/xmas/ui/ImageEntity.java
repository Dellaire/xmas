package net.sprd.xmas.ui;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ImageEntity {

	@Id
	private String id;
	
	private byte[] content;

	public String getId() {
		return id;
	}

	public ImageEntity setId(String id) {
		this.id = id;
		return this;
	}

	public byte[] getContent() {
		return content;
	}

	public ImageEntity setContent(byte[] content) {
		this.content = content;
		return this;
	}
}
