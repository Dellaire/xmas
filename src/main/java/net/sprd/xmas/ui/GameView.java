package net.sprd.xmas.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import net.sprd.xmas.logic.CallbackBroker;
import net.sprd.xmas.logic.Challenge;
import net.sprd.xmas.logic.Score;
import net.sprd.xmas.repositories.ChallengeRepository;
import net.sprd.xmas.repositories.ScoreRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Route("xmas/fraudgame")
public class GameView extends VerticalLayout {

    private final ChallengeRepository challengeRepository;
    private final ScoreRepository scoreRepository;
    private final CallbackBroker callbackBroker;

    private List<Challenge> challenges;
    private String playerName;
    private BigDecimal playerError;
    private Integer count;

    public GameView(ChallengeRepository challengeRepository, ScoreRepository scoreRepository,
                    CallbackBroker callbackBroker) {

        this.challengeRepository = challengeRepository;
        this.scoreRepository = scoreRepository;
        this.callbackBroker = callbackBroker;

        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        this.setHeightFull();

        this.displayStartPage();
    }

    private void displayStartPage() {

        Label placeholder = new Label("");
        placeholder.setHeight("25%");

        Label introductionText1 = new Label(
                "...");
        introductionText1.setMaxWidth("50%");
        introductionText1.getStyle().set("font-size", "25px");

        Label introductionText2 = new Label("...");
        introductionText2.getStyle().set("font-size", "25px");

        Label introductionText3 = new Label("...");
        introductionText3.getStyle().set("font-size", "25px");

        TextField playerNameInput = new TextField("Spreadshirt username");
        playerNameInput.setAutofocus(true);

        Button button = new Button("Start");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(event -> {

            if ((playerNameInput.getValue() != null) && (!playerNameInput.getValue().isEmpty())) {

                this.playerName = playerNameInput.getValue();
                if (this.scoreRepository.existsByName(this.playerName)) {

                    playerNameInput.clear();
                    Dialog errorDialog = new Dialog();
                    errorDialog.add(new Label("This player already played!"));
                    errorDialog.open();

                } else {
                    this.challenges = this.challengeRepository.findAll();
                    Collections.shuffle(this.challenges);
                    this.playerError = new BigDecimal(0);
                    this.count = 0;

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

        Label count = new Label(++this.count + " / 17");
        count.getStyle().set("font-size", "25px");

        TextField guessInput = new TextField("Solvency Score");
        guessInput.setAutofocus(true);

        Button button = new Button("Submit");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(event -> {

            if ((guessInput.getValue() != null) && (!guessInput.getValue().isEmpty())) {
                this.playerError = this.playerError.add(new BigDecimal(
                        Math.abs(challenge.getSolvencyScore() - Double.parseDouble(guessInput.getValue().replace(',', '.')))));
                this.displaySolvencyScoreImage(challenge);
            }
        });

        this.removeAll();
        this.add(placeholder);
        this.add(count);
        this.add(challenge.getFraudSuspectAsImage());

        this.add(guessInput);
        this.add(button);
    }

    private void displaySolvencyScoreImage(Challenge challenge) {

        Label placeholder = new Label("");
        placeholder.setHeight("30%");

        Button button = new Button("Next");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(event -> {

            if (this.challenges.size() > 0) {
                this.displayFraudSuspectImage();
            } else {
                this.playerError = this.playerError.setScale(2, RoundingMode.HALF_UP);
                this.scoreRepository.save(new Score().setName(this.playerName).setError(this.playerError).setDateCreated(ZonedDateTime.now()));
                this.callbackBroker.callback();
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

        Label endingText1 = new Label("Thank you for your contribution, your error score is ...");
        endingText1.getStyle().set("font-size", "25px");
        Label endingText2 = new Label(this.playerError + "");
        endingText2.getStyle().set("font-size", "25px");

        Button button = new Button("Finish");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(event -> this.displayStartPage());

        this.removeAll();
        this.add(placeholder);
        this.add(endingText1);
        this.add(endingText2);
        this.add(button);
    }
}
