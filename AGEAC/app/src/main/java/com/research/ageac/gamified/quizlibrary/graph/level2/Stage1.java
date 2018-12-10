package com.research.ageac.gamified.quizlibrary.graph.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Basics of Graph\n2. Properties of Graph";

    private String mQuestions[] = {
            "A directed graph is ____ if there is a path from each vertex to every other vertex in the digraph\n",
            "If every node u in undirected G is adjacent to every other node v in G, A graph is said to be\n",
            "A graph is said to be ____ if the vertices can be split into two sets V1 and V2 such there are no edges between two vertices of V1 or two vertices of V2\n",
            "For an undirected graph with n vertices and e edges, the sum of the degree of all vertices is\n",
            "The number of edges in a simple, n-vertex, complete graph is\n"
    };

    private String mChoices[][] = {
            {"Weakly connected", "Strongly Connected", "Tightly Connected", "Linearly Connected"},
            {"Isolated", "Strongly connected", "Complete", "Finite"},
            {"Partite", "Bipartite", "Rooted", "Bisects"},
            {"2n", "(2n-1)/2", "2e", "(e^2)/2"},
            {"n*(n-2)", "n*(n-1)", "n*(n-1)/2", "n*(n-1)*(n-2)"}
    };

    private int mCorrectAnswers[] = {1, 2, 1, 2, 2};

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