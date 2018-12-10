package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Graph";

    private String mQuestions[] = {
            "A graph with n vertices will definitely have a parallel edge or self loop if the total number of edges are\n",
            "The number of edges in a regular graph of degree d and n vertices is\n",
            "What is the largest integer m such that every simple connected graph with n vertices and n edges contains at least m different spanning trees?\n",
            "The degree sequence of a simple graph is the sequence of the degrees of the nodes in the graph in decreasing order. Which of the following sequences can not be the degree sequence of any graph? \n" +
                    "\nI. 7, 6, 5, 4, 4, 3, 2, 1 \n" +
                    "II. 6, 6, 6, 6, 3, 3, 2, 2 \n" +
                    "III. 7, 6, 6, 4, 4, 3, 2, 2\n" +
                    "IV. 8, 7, 7, 6, 4, 2, 1, 1\n",
            "The most efficient algorithm for finding the number of connected components in an undirected graph on n vertices and m edges has time complexity\n"
    };

    private String mChoices[][] = {
            {"more than n", "more than n+1", "more than (n+1)/2", "more than n(n-1)/2"},
            {"maximum of n,d", "n+d", "nd/2", "nd"},
            {"1", "2", "3", "n"},
            {"I and II", "III and IV", "IV only", "II and IV"},
            {"Θ(n)", "Θ(m)", "Θ(m+n)", "Θ(mn)"}
    };

    private int mCorrectAnswers[] = {3, 2, 2, 3, 2};

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