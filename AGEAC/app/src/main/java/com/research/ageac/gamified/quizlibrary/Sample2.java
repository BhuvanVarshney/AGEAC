package com.research.ageac.gamified.quizlibrary;

public class Sample2 extends TrueFalseBaseClass {

    private String prerequisites = "1. Sorting";

    private String mQuestions[] = {
            "",
            "",
            "",
            "",
            ""
    };

    private String mChoices[][] = {
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""},
            {"", ""}
    };

    private int mCorrectAnswers[] = {2, 0, 3, 1, 1};

    public String getPrerequisites() {
        return prerequisites;
    }

    public int getQuestionsCount() {
        return mQuestions.length;
    }

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = mChoices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = mChoices[a][1];
        return choice2;
    }

    public String getChoice3(int a) {
        String choice3 = mChoices[a][2];
        return choice3;
    }

    public String getChoice4(int a) {
        String choice4 = mChoices[a][3];
        return choice4;
    }

    public int getCorrectAnswer(int a) {
        return mCorrectAnswers[a];
    }

}