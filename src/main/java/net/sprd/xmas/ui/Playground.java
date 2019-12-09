package net.sprd.xmas.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.sprd.xmas.logic.Challenge;
import net.sprd.xmas.logic.Score;
import net.sprd.xmas.repositories.ChallengeRepository;
import net.sprd.xmas.repositories.ScoreRepository;

import java.util.Random;

@Route("xmas")
public class Playground extends VerticalLayout {

    private final ChallengeRepository challengeRepository;
    private final ScoreRepository scoreRepository;

    private Challenge challenge;

    public Playground(ChallengeRepository challengeRepository, ScoreRepository scoreRepository) {

        this.challengeRepository = challengeRepository;
        this.scoreRepository = scoreRepository;

        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        this.setHeightFull();

        this.displayFraudSuspectImage();
    }

    private void displayFraudSuspectImage() {

        String challengeId = new Random().nextInt(new Long(this.challengeRepository.count()).intValue()) + "";
        this.challenge = this.challengeRepository.findById(challengeId).get();

        Label label = new Label("");
        label.setHeight("25%");

        TextField playerNameInput = new TextField("Player Name");
        TextField guessInput = new TextField("Solvency Score");

        Button button = new Button("Submit");
        button.addClickListener(event -> {
            String playerName = playerNameInput.getValue();
            Double error = Math.abs(this.challenge.getSolvencyScore() - Double.parseDouble(guessInput.getValue()));
            this.scoreRepository.save(new Score().setName(playerName).setError(error));
        });
        button.addClickListener(event -> this.displaySolvencyScoreImage());

        this.removeAll();
        this.add(label);
        this.add(this.challenge.getFraudSuspectAsImage());

        this.add(playerNameInput);
        this.add(guessInput);
        this.add(button);
    }

    private void displaySolvencyScoreImage() {

        Label label = new Label("");
        label.setHeight("30%");

        Button button = new Button("Restart");
        button.addClickListener(event -> this.displayFraudSuspectImage());

        this.removeAll();
        this.add(label);
        this.add(this.challenge.getSolvencyScoreAsImage());
        this.add(button);
    }
}
