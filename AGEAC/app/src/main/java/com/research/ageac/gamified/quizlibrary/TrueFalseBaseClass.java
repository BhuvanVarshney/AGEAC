package com.research.ageac.gamified.quizlibrary;

public class TrueFalseBaseClass extends PrerequisitesBaseClass {

    private String prerequisites;

    private String questions[];

    private String choices[][];

    private int correctAnswers[];

    public String getPrerequisites() {
        return prerequisites;
    }

    public int getQuestionsCount() {
        return questions.length;
    }

    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = choices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = choices[a][1];
        return choice2;
    }

    public int getCorrectAnswer(int a) {
        return correctAnswers[a];
    }
}

