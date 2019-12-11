package net.sprd.xmas.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import net.sprd.xmas.logic.Challenge;
import net.sprd.xmas.logic.Score;
import net.sprd.xmas.repositories.ChallengeRepository;
import net.sprd.xmas.repositories.ScoreRepository;

import java.util.Collections;
import java.util.List;

@Route("xmas/fraudgame")
@StyleSheet("style.scss")
public class GameView extends VerticalLayout {

    private final ChallengeRepository challengeRepository;
    private final ScoreRepository scoreRepository;

    private List<Challenge> challenges;
    private String playerName;
    private Double playerError;

    public GameView(ChallengeRepository challengeRepository, ScoreRepository scoreRepository) {

        this.challengeRepository = challengeRepository;
        this.scoreRepository = scoreRepository;

        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        this.setHeightFull();

        this.displayStartPage();
    }

    private void displayStartPage() {

        Label placeholder = new Label("");
        placeholder.setHeight("25%");

        Label introductionText1 = new Label(
                "...");
        introductionText1.setMaxWidth("30%");
        introductionText1.setId("introduction");

        Label introductionText2 = new Label("...");

        Label introductionText3 = new Label("...");

        TextField playerNameInput = new TextField("...");

        Button button = new Button("Start");
        button.addClickListener(event -> {

            if (playerNameInput.getValue() != null) {

                this.playerName = playerNameInput.getValue();
                if (this.scoreRepository.existsByName(this.playerName)) {

                    playerNameInput.clear();
                    Dialog errorDialog = new Dialog();
                    errorDialog.add(new Label("This player already played!"));
                    errorDialog.open();

                } else {
                    this.challenges = this.challengeRepository.findAll();
                    Collections.shuffle(this.challenges);
                    this.playerError = 0.0;

                    this.displayFraudSuspectImage();
                }
            }
        });

        this.removeAll();
        this.add(placeholder);
        this.add(introductionText1);
        this.add(introductionText2);
        this.add(introductionText3);

        this.add(playerNameInput);
        this.add(button);
    }

    private void displayFraudSuspectImage() {

        Challenge challenge = this.challenges.remove(0);

        Label placeholder = new Label("");
        placeholder.setHeight("25%");

        TextField guessInput = new TextField("Solvency Score");

        Button button = new Button("Submit");
        button.addClickListener(event -> {

            this.playerError += Math.abs(challenge.getSolvencyScore() - Double.parseDouble(guessInput.getValue()));
            this.displaySolvencyScoreImage(challenge);
        });

        this.removeAll();
        this.add(placeholder);
        this.add(challenge.getFraudSuspectAsImage());

        this.add(guessInput);
        this.add(button);
    }

    private void displaySolvencyScoreImage(Challenge challenge) {

        Label placeholder = new Label("");
        placeholder.setHeight("30%");

        Button button = new Button("Next");
        button.addClickListener(event -> {

            if (this.challenges.size() > 0) {
                this.displayFraudSuspectImage();
            } else {
                this.scoreRepository.save(new Score().setName(this.playerName).setError(this.playerError));
                this.displayEndScreen();
            }
        });

        this.removeAll();
        this.add(placeholder);
        this.add(challenge.getSolvencyScoreAsImage());
        this.add(button);
    }

    private void displayEndScreen() {

        Label placeholder = new Label("");
        placeholder.setHeight("30%");

        Label endingText = new Label("Thank you for your contribution!");

        Button button = new Button("Finish");
        button.addClickListener(event -> this.displayStartPage());

        this.removeAll();
        this.add(placeholder);
        this.add(endingText);
        this.add(button);
    }
}
