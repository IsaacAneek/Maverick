package com.example.maverick;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class PomodoroViewController {

    public Label taskTitleLabel;
    public ProgressBar progressBar;
    public TextField focusTextField;
    public TextField breakTextField;
    public Label remainingTimeLabel;

    private Timeline timeline;
    private int totalSeconds;
    private int remainingSeconds;

    public void onFocusButtonClicked(ActionEvent actionEvent) {
        startTimerFromTextField(focusTextField, "Focus Time");
    }

    public void onBreakButtonClicked(ActionEvent actionEvent) {
        startTimerFromTextField(breakTextField, "Break Time");
    }

    private void startTimerFromTextField(TextField textField, String title) {
        if (timeline != null) {
            timeline.stop();
        }

        int minutes;
        try {
            minutes = Integer.parseInt(textField.getText());
            if (minutes <= 0) return;
        } catch (NumberFormatException e) {
            return;
        }

        taskTitleLabel.setText(title);

        totalSeconds = minutes * 60;
        remainingSeconds = totalSeconds;
        progressBar.setProgress(0);

        updateTimeLabel();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;
            updateTimeLabel();
            updateProgressBar();

            if (remainingSeconds <= 0) {
                timeline.stop();
                remainingTimeLabel.setText("00:00");
                progressBar.setProgress(1.0);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimeLabel() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        remainingTimeLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateProgressBar() {
        double progress = 1.0 - ((double) remainingSeconds / totalSeconds);
        progressBar.setProgress(progress);
    }
}
