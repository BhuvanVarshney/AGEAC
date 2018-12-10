package com.research.ageac.gamified;

public class LeaderboardDTO {
    private String username;
    private double completion;
    private int xp;
    private double totalAccuracy;
    private int daysStreak;
    private int assignmentsCompleted;
    private int rank;

    public int getDaysStreak() {
        return daysStreak;
    }

    public void setDaysStreak(int daysStreak) {
        this.daysStreak = daysStreak;
    }

    public int getAssignmentsCompleted() {
        return assignmentsCompleted;
    }

    public void setAssignmentsCompleted(int assignmentsCompleted) {
        this.assignmentsCompleted = assignmentsCompleted;
    }

    public double getCompletion() {
        return completion;
    }

    public void setCompletion(double completion) {
        this.completion = completion;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public double getTotalAccuracy() {
        return totalAccuracy;
    }

    public void setTotalAccuracy(double totalAccuracy) {
        this.totalAccuracy = totalAccuracy;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
