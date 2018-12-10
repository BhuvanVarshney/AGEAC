package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Graph\n2. Searching in Graph";

    private String mQuestions[] = {
            "How many undirected graphs (not necessarily connected) can be constructed out of a given set V = {V1, V2, … Vn} of n vertices ?\n",
            "Consider an undirected random graph of eight vertices. The probability that there is an edge between a pair of vertices is 1/2. What is the expected number of unordered cycles of length three?\n",
            "You are given a graph containing n vertices and m edges and given that the graph doesn’t contain cycle of odd length. Time Complexity of the best known algorithm to find out whether the graph is bipartite or not is\n",
            "Let G be the graph with 100 vertices numbered 1 to 100. Two vertices i and j are adjacent iff\n\n|i−j|=8 or |i−j|=12\n\nThe number of connected components in G is\n",
            "Which of the following algorithms can be used to most efficiently determine the presence of a cycle in a given graph ?\n"
    };

    private String mChoices[][] = {
            {"n(n-1)/2", "2^n", "n!", "2^(n(n-1)/2)"},
            {"1/8", "1", "7", "8"},
            {"O(m+n)", "O(1)", "O(mn)", "O(n^2)"},
            {"4", "8", "12", "25"},
            {"Prim's Minimum Spanning Tree Algorithm", "Kruskal' Minimum Spanning Tree Algorithm", "Depth First Search", "Breadth First Search"}
    };

    private int mCorrectAnswers[] = {3, 2, 1, 0, 2};

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