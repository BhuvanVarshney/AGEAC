package com.research.ageac.gamified.quizlibrary.graph.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Graph";

    private String mQuestions[] = {
            "The maximum degree of any vertex in a simple graph with n vertices is\n",
            "The minimum number of edges in a connected cyclic undirected graph on n vertices is\n",
            "What is the maximum number of edges in an acyclic undirected graph with n vertices?\n",
            "Which of the following statements is/are TRUE for an undirected graph?\nP: Number of odd degree vertices is even \n" +
                    "Q: Sum of degrees of all vertices is even\n",
            "Given two vertices v1 and v2 in a graph G, path from v1 to v2 can be found using\n"
    };

    private String mChoices[][] = {
            {"n–1", "n+1", "2n–1", "n"},
            {"n-1", "n", "n+1", "None of the above"},
            {"n-1", "n", "n+1", "2n-1"},
            {"P Only", "Q Only", "Both P and Q", "Neither P nor Q"},
            {"Only BFS", "Only DFS", "Either BFS or DFS", "Neither BFS nor DFS"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 2, 2};

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