package me.alextodea.sandbox.ui;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import me.alextodea.sandbox.domain.Results;
import me.alextodea.sandbox.logic.ResultCalculator;

public class UserInterface {
    private BorderPane layout;

    public UserInterface() {
        layout = new BorderPane();
        Results simpleResults = new Results();
        Results yearlyInterestResults = new Results();
        final double[] monthlyInvestmentValue = {25.0};
        final double[] yearlyInterestRateValue = {0.0};
        final ResultCalculator[] resultCalculator = {new ResultCalculator()};

        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis();
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setTitle("Savings calculator");

        layout.setCenter(lineChart);

        VBox topComponent = new VBox();
        topComponent.setSpacing(10);
        topComponent.setPadding(new Insets(10, 10, 10, 10));

        BorderPane monthlySavings = new BorderPane();
        Label monthlySavingsText = new Label("Monthly savings");
        Slider monthlySavingsSlider = new Slider(25, 250, 25);
        monthlySavingsSlider.setShowTickMarks(true);
        monthlySavingsSlider.setShowTickLabels(true);
        monthlySavingsSlider.setMajorTickUnit(25);
        monthlySavingsSlider.setBlockIncrement(25);
        monthlySavingsSlider.setSnapToTicks(false);
        Label currentFirstSliderValue = new Label(String.valueOf(monthlyInvestmentValue[0]));

        resultCalculator[0] = new ResultCalculator(simpleResults, 25.0, 0.0);
        resultCalculator[0].calculateResults();
        lineChart.getData().add(simpleResults.getAsSeries());
        lineChart.setLegendVisible(false);

        monthlySavingsSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {

            monthlyInvestmentValue[0] = newValue.doubleValue();

            currentFirstSliderValue.setText(String.format("%.2f", monthlyInvestmentValue[0]));

            resultCalculator[0] = new ResultCalculator(simpleResults, monthlyInvestmentValue[0], 0.0);
            resultCalculator[0].calculateResults();

            lineChart.getData().remove(0);
            lineChart.getData().add(simpleResults.getAsSeries());
        });

        monthlySavings.setLeft(monthlySavingsText);
        monthlySavings.setCenter(monthlySavingsSlider);
        monthlySavings.setRight(currentFirstSliderValue);

        BorderPane yearlyInterestRate = new BorderPane();
        Label yearlyInterestRateText = new Label("Yearly interest rate");
        Slider yearlyInterestRateSlider = new Slider(0, 10, 0);
        yearlyInterestRateSlider.setShowTickMarks(true);
        yearlyInterestRateSlider.setShowTickLabels(true);
        Label currrentSecondSliderValue = new Label(String.valueOf(yearlyInterestRateValue[0]));

        resultCalculator[0] = new ResultCalculator(yearlyInterestResults, 50.0, 10.0);
        resultCalculator[0].calculateResults();
        lineChart.getData().add(yearlyInterestResults.getAsSeries());

        yearlyInterestRateSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {

            yearlyInterestRateValue[0] = newValue.doubleValue();

            currrentSecondSliderValue.setText(String.format("%.2f", yearlyInterestRateValue[0]));

            resultCalculator[0] = new ResultCalculator(yearlyInterestResults, monthlyInvestmentValue[0], yearlyInterestRateValue[0]);
            resultCalculator[0].calculateResults();

            lineChart.getData().remove(1);
            lineChart.getData().add(yearlyInterestResults.getAsSeries());
        });

        yearlyInterestRate.setLeft(yearlyInterestRateText);
        yearlyInterestRate.setCenter(yearlyInterestRateSlider);
        yearlyInterestRate.setRight(currrentSecondSliderValue);

        topComponent.getChildren().addAll(monthlySavings, yearlyInterestRate);

        layout.setTop(topComponent);
    }

    public BorderPane getLayout() {
        return layout;
    }
}
