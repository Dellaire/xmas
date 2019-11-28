package net.sprd.xmas.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("xmas")
public class Playground extends VerticalLayout {

	private Integer number1;
	private Integer number2;
	
	public Playground() throws IOException {

		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.setHeightFull();
		
		this.displayImage1();
	}
	
	private void displayImage1() {
		
		Label label = new Label("");
		label.setHeight("25%");
		
		TextField textField = new TextField("Number 1");
		
		Button button = new Button("Next");
		button.addClickListener(event -> this.number1 = Integer.parseInt(textField.getValue()));
		button.addClickListener(event -> this.displayImage2());
		
		this.removeAll();
		this.add(label);
		try {
			this.add(this.readImage("image1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(textField);
		this.add(button);
	}
	
	private void displayImage2() {
		
		Label label = new Label("");
		label.setHeight("25%");
		
		TextField textField = new TextField("Number 2");
		
		Button button = new Button("Finish");
		button.addClickListener(event -> this.number2 = Integer.parseInt(textField.getValue()));
		button.addClickListener(event -> this.displayResult());
		
		this.removeAll();
		this.add(label);
		try {
			this.add(this.readImage("image2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.add(textField);
		this.add(button);
	}
	
	private void displayResult() {
		
		Label label = new Label("");
		label.setHeight("30%");
		
		Button button = new Button("Restart");
		button.addClickListener(event -> this.displayImage1());
		
		this.removeAll();
		this.add(label);
		this.add(new Label(this.number1 + this.number2 + ""));
		this.add(button);
	}
	
	private Image readImage(String imageName) throws IOException {
		
		String imagePath = this.getClass().getClassLoader().getResource(imageName).getFile();
		if(imagePath.startsWith("/")) {
			imagePath = imagePath.substring(1);
		}
		
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		StreamResource resource = new StreamResource(imageName, () -> new ByteArrayInputStream(imageBytes));
		Image image = new Image(resource, "error");
		
		return image;
	}
}
