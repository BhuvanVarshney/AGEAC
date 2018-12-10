package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage5 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Graph\n2. Spanning Trees";

    private String mQuestions[] = {
            "A complete graph can have\n",
            "To implement Dijkstra’s shortest path algorithm on unweighted graphs so that it runs in linear time, the data structure to be used is\n",
            "Consider a weighted complete graph G on the vertex set {v1, v2, ... vn} such that the weight of the\n edge (vi, vj) is 2|i-j|.\nThe weight of a minimum spanning tree of G is\n",
            "Let G = (V, E) be a simple undirected graph, and s be a particular vertex in it called the source.\nFor x ∈ V, let d(x) denote the shortest distance in G from s to x. A breadth first search (BFS) is performed starting at s. Let T be the resultant BFS tree. If (u, v) is an edge of G that is not in T, then which one of the following CANNOT be the value of\nd(u) – d(v)?\n",
            "Consider a directed graph with n vertices and m edges such that all edges have same edge weights. Find the complexity of the best known algorithm to compute the minimum spanning tree of the graph?\n"
    };

    private String mChoices[][] = {
            {"n^2 spanning trees", "n^(n-2) spanning trees", "n^(n+1) spanning trees", "n^n spanning trees"},
            {"Queue", "Stack", "Heap", "B-Tree"},
            {"2", "n - 1", "2(n-1)", "nC2"},
            {"-1", "0", "1", "2"},
            {"O(m+n)", "O(mlogn)", "O(mn)", "O(nlogm)"}
    };

    private int mCorrectAnswers[] = {1, 0, 2, 3, 0};

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