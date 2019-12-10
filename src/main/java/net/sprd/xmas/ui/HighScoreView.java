package net.sprd.xmas.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import net.sprd.xmas.logic.Score;
import net.sprd.xmas.repositories.ScoreRepository;

@Route("xmas/highscores")
public class HighScoreView extends VerticalLayout {

    private final ScoreRepository scoreRepository;

    public HighScoreView(ScoreRepository scoreRepository) {

        this.scoreRepository = scoreRepository;

        Grid<Score> scoreGrid = new Grid<>(Score.class);
        scoreGrid.setItems(this.scoreRepository.findAllByOrderByErrorAsc());
        scoreGrid.setColumns("name", "error");

        this.add(new Label("Highscores"));
        this.add(scoreGrid);
    }
}
