package com.research.ageac.gamified;

public class StageAttemptsDTO {
    private int totalAttempts;
    private int failedAttempts;
    private int questionsAttempted;
    private int questionsAnsweredWrong;

    public int getQuestionsAnsweredWrong() {
        return questionsAnsweredWrong;
    }

    public void setQuestionsAnsweredWrong(int questionsAnsweredWrong) {
        this.questionsAnsweredWrong = questionsAnsweredWrong;
    }

    public int getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(int questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }
}
