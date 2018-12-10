package com.research.ageac.gamified.quizlibrary.graph.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Basics of Graph\n2. Properties of Graph";

    private String mQuestions[] = {
            "A graph is said to be complete if there is an edge between every pair of vertices\n",
            "An undirected graph which contains no cycles is called a forest\n",
            "Network is a graph that has weights or costs associated with it\n",
            "In an undirected graph, number of odd degree vertices is\n",
            "In an undirected graph, sum of degrees of all vertices is\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"Odd", "Even"},
            {"Odd", "Even"}
    };

    private int mCorrectAnswers[] = {0, 0, 0, 1, 1};

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