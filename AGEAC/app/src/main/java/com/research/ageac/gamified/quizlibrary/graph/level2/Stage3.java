package com.research.ageac.gamified.quizlibrary.graph.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Spanning Tree";

    private String mQuestions[] = {
            "From a complete graph with n vertices and e edges, we can construct a spanning tree by removing maximum ___ edges\n",
            "If we choose Prim's Algorithm for uniquely weighted spanning tree instead of Kruskal's Algorithm, then\n",
            "All possible spanning trees of graph G\n",
            "Which algorithm solves the all pairs shortest path problem ?\n",
            "Given a weighted graph with n vertices, where weights of all edges are unique, there ____ shortest path(s) from a source to destination in such a graph\n"
    };

    private String mChoices[][] = {
            {"e-n+1", "n-e+1", "n+e-1", "e-n-1"},
            {"we'll get a different spanning tree", "we'll get the same spanning tree", "spanning will have less edges", "spanning will not cover all vertices"},
            {"have same number of edges and vertices", "have same number of edges and but not vertices", "have same number of vertices but not edges", "depends upon algorithm being used"},
            {"Floyd Warshall’s algorithm", "Prim’s algorithm", "Both (A) & (B)", "None of the above"},
            {"will be no unique", "will be a unique", "may be more than 1", "will be exactly n-1"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 0, 2};

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