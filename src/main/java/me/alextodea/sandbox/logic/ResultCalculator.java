package me.alextodea.sandbox.logic;

import me.alextodea.sandbox.domain.Results;

public class ResultCalculator {
    private Results results;
    private double yearlyInvestment;
    private double interestRate;

    public ResultCalculator(Results results, double yearlyInvestment, double interestRate) {
        this.results = results;
        this.yearlyInvestment = yearlyInvestment;
        this.interestRate = interestRate;
    }

    public ResultCalculator() {

    }

    public void calculateResults() {
        for (int i = 0; i < 31; i++) {
            if (i == 0) {
                results.setResultForYear(i, 0);
            } else {
                double baseSum = results.getResultForYear(i-1) + yearlyInvestment*12;
                results.setResultForYear(i, baseSum + (interestRate*baseSum)/100);
            }
        }
    }

    public double[] getResults() {
        return results.getResults();
    }
}
