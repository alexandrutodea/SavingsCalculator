package me.alextodea.sandbox.domain;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Results {
    double[] years;

    public Results() {
        years = new double[31];
    }

    public double[] getResults() {
        return years;
    }

    public double getResultForYear(int i) {
        return years[i];
    }

    public void setResultForYear(int i, double resultForYear) {
        years[i] = resultForYear;
    }

    public XYChart.Series getAsSeries() {
        XYChart.Series data = new XYChart.Series();

        for (int i = 0; i < 31; i++) {
            data.getData().add(new XYChart.Data(i, years[i]));
        }

        return data;
    }
}