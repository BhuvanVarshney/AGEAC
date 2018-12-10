package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Graph";

    private String mQuestions[] = {
            "Let G be a graph with n vertices and m edges. What is the tightest upper bound on the running time on Depth First Search of G? Assume that the graph is represented using adjacency matrix\n",
            "In an unweighted, undirected connected graph, the shortest path from a node S to every other node is computed most efficiently, in terms of time complexity by\n",
            "Which of the following is the least efficient in a cyclic, unweighted, undirected connected graph\n",
            "Given a directed graph where weight of every edge is same, we can efficiently find shortest path from a given source to destination using?\n",
            "Let G be a directed graph whose vertex set is the set of numbers from 1 to 100. There is an edge from a vertex i to a vertex j iff\n\nj = i + 1 or j = 3i\n\nThe minimum number of edges in a path in G from vertex 1 to vertex 100 is\n"
    };

    private String mChoices[][] = {
            {"O(n)", "O(m+n)", "O(n^2)", "O(mn)"},
            {"Dijkstra’s algorithm starting from S", "Warshall’s algorithm", "Performing a DFS starting from S", "Performing a BFS starting from S"},
            {"BFS", "DFS", "Warshall’s algorithm", "Dijkastra’s algorithm"},
            {"Breadth First Traversal", "Depth First Traversal", "Dijkstra's Shortest Path Algorithm", "Neither Breadth First Traversal nor Dijkstra's algorithm can be used"},
            {"4", "7", "23", "99"}
    };

    private int mCorrectAnswers[] = {2, 3, 1, 0, 1};

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