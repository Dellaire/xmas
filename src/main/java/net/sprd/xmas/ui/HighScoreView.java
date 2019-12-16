package net.sprd.xmas.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import net.sprd.xmas.logic.CallbackBroker;
import net.sprd.xmas.logic.Score;
import net.sprd.xmas.repositories.ScoreRepository;

@Route("xmas/highscores")
@Push
public class HighScoreView extends VerticalLayout {

    private final ScoreRepository scoreRepository;
    private final CallbackBroker callbackBroker;

    private UI ui;

    public HighScoreView(ScoreRepository scoreRepository, CallbackBroker callbackBroker) {

        this.scoreRepository = scoreRepository;
        this.callbackBroker = callbackBroker;

        Grid<Score> scoreGrid = new Grid<>(Score.class);
        scoreGrid.setItems(this.scoreRepository.findAllByOrderByErrorAsc());
        scoreGrid.setColumns("name", "error", "dateCreated");
        scoreGrid.setHeightByRows(true);

        this.callbackBroker.register(str -> {
            this.ui.getPage().reload();
        });

        this.add(new Label("Highscores"));
        this.add(scoreGrid);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {

        this.ui = attachEvent.getUI();
    }
}
