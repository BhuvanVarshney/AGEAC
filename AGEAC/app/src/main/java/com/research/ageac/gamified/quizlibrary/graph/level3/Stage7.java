package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage7 extends MCQBaseClass {

    private String prerequisites = "1. Spanning Trees\n2. Shortest Path Algorithms";

    private String mQuestions[] = {
            "In a weighted graph, assume that the shortest path from a source 's' to a destination 't' is correctly calculated using a shortest path algorithm. If we increase weight of every edge by n(n>0), then\n",
            "An undirected graph G has n nodes. Its adjacency matrix is given by an n×n square matrix whose\n\n(i) diagonal elements are 0‘s\nand\n(ii) non-diagonal elements are 1‘s\n\nwhich one of the following is TRUE?\n",
            "Let G be an undirected connected graph with distinct edge weight. Let emax be the edge with maximum weight and emin the edge with minimum weight. Which of the following statements is false?\n",
            "Let G be a weighted graph with edge weights greater than one and G' be the graph constructed by squaring the weights of edges in G. Let T and T' be the minimum spanning trees of G and G', respectively, with total weights t and t'. Which of the following statements is TRUE?\n",
            "G = (V, E) is an undirected simple graph in which each edge has a distinct weight, and e is a particular edge of G. Which of the following statements about the minimum spanning trees (MSTs) of G is/are TRUE\n" +
                    "\n" +
                    "I. If e is the lightest edge of some cycle in G, then every MST of G includes e\n" +
                    "II. If e is the heaviest edge of some cycle in G, then every MST of G excludes e\n"
    };

    private String mChoices[][] = {
            {"Shortest path will remain same in every case", "Shortest path may change", "Weight sum along the path will change in every case", "None of the above"},
            {"Graph G has no minimum spanning tree (MST)", "Graph G has a unique MST of cost n-1", "Graph G has multiple distinct MSTs, each of cost n-1", "Graph G has multiple spanning trees of different costs"},
            {"Every minimum spanning tree of G must contain emin", "If emax is in a minimum spanning tree, then its removal must disconnect G", "No minimum spanning tree contains emax", "G has a unique minimum spanning tree"},
            {"T' = T with total weight t' = t2", "T' = T with total weight t' < t2", "T' != T but total weight t' = t2", "None of the above"},
            {"I Only", "II Only", "Both I and II", "Neither I nor II"}
    };

    private int mCorrectAnswers[] = {1, 2, 2, 3, 1};

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