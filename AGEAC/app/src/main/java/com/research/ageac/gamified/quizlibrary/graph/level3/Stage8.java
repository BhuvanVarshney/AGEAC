package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage8 extends MCQBaseClass {

    private String prerequisites = "1. Spanning Trees\n2. Shortest Path Algorithms";

    private String mQuestions[] = {
            "Given a graph where all edges have positive weights, the shortest paths produced by Dijsktra and Bellman Ford algorithm\n",
            "Let G be a weighted undirected graph and e be an edge with maximum weight in G. Suppose there is a minimum weight spanning tree in G containing the edge e. Which of the following statements is always TRUE?\n",
            "Let G be an undirected graph. Consider a depth-first traversal of G, and let T be the resulting depth-first search tree. Let u be a vertex in G and let v be the first new (unvisited) vertex visited after visiting u in the traversal. Which of the following statements is always true?\n",
            "Consider the tree arcs of a BFS traversal from a source node W in an unweighted, connected, undirected graph. The tree T formed by the tree arcs is a data structure for computing\n",
            "In an adjacency list representation of an undirected simple graph G = (V, E), each edge (u, v) has two adjacency list entries:\n[v] in the adjacency list of u, and\n[u] in the adjacency list of v.\nThese are called twins of each other. A twin pointer is a pointer from an adjacency list entry to its twin.\nIf |E| = m and |V| = n, and the memory size is not a constraint, what is the time complexity of the most efficient algorithm to set the twin pointer in each entry in each adjacency list?\n"
    };

    private String mChoices[][] = {
            {"Will be same with same path weight", "Will be same but path weight will be different", "May be different but path weight would always be same", "May be different with different path weight"},
            {"There exists a cutset in G having all edges of maximum weight", "There exists a cycle in G having all edges of maximum weight", "Edge e cannot be contained in a cycle", "All edges in G have the same weight"},
            {"{u,v} must be an edge in G, and u is a descendant of v in T", "{u,v} must be an edge in G, and v is a descendant of u in T", "If {u,v} is not an edge in G then u is a leaf in T", "If {u,v} is not an edge in G then u and v must have the same parent in T"},
            {"The shortest path between every pair of vertices", "The shortest path from W to every vertex in the graph", "The shortest paths from W to only those nodes that are leaves of T", "The longest path in the graph"},
            {"Θ(n^2)", "Θ(n^4)", "Θ(m^2)", "Θ(m+n)"}
    };

    private int mCorrectAnswers[] = {2, 0, 2, 1, 3};

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