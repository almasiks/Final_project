package model.academic;

import java.io.Serializable;

public class Mark implements Serializable {
    private double firstAttestation;
    private double secondAttestation;
    private double finalExam;

    public Mark(double firstAttestation, double secondAttestation, double finalExam) {
    }


    public double getTotalScore() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public String getGradeLetter() {
        double totalScore = getTotalScore();
        if (95 <= totalScore && totalScore <= 100) {
            return "A";
        }
        if (90 <= totalScore && totalScore <= 94) {
            return "A-";
        }
        if (85 <= totalScore && totalScore <= 89) {
            return "B+";
        }
        if (80 <= totalScore && totalScore <= 84) {
            return "B";
        }
        if (75 <= totalScore && totalScore <= 79) {
            return "B-";
        }
        if (70 <= totalScore && totalScore <= 74) {
            return "C+";
        }
        if (65 <= totalScore && totalScore <= 69) {
            return "C";
        }
        if (60 <= totalScore && totalScore <= 64) {
            return "C-";
        }
        if (55 <= totalScore && totalScore <= 59) {
            return "D+";
        }
        if (50 <= totalScore && totalScore <= 54) {
            return "D";
        }
        return "F";
    }

    @Override
    public String toString() {
        return "Mark{" + getGradeLetter() + " (" + getTotalScore() + ")}";
    }














}
