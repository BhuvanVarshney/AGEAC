package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Graph";

    private String mQuestions[] = {
            "Consider a weighted undirected graph with positive edge weights and let (u,v) be an edge in the graph. It is known that the shortest path from the source vertex s to u has weight 53 and the shortest path from s to v has weight 65. Which one of the following statements is always true?\n",
            "Consider an undirected unweighted graph G. Let a breadth-first traversal of G be done starting from a node r. Let d(r, u) and d(r, v) be the lengths of the shortest paths from r to u and v respectively, in G. lf u is visited before v during the breadth-first traversal, which of the following statements is correct?\n",
            "Which of the following is an advantage of adjacency list representation over adjacency matrix representation of a graph?\n",
            "Let T be a depth first search tree in an undirected graph G. Vertices u and n are leaves of this tree T. The degrees of both u and n in G are at least 2. which one of the following statements is true?\n",
            "In a depth-first traversal of a graph G with n vertices, k edges are marked as tree edges. The number of connected components in G is\n"
    };

    private String mChoices[][] = {
            {"weight (u, v) < 12", "weight (u, v) ≤ 12", "weight (u, v) > 12", "weight (u, v) ≥ 12"},
            {"d(r, u) < d (r, v)", "d(r, u) > d(r, v)", "d(r, u) <= d (r, v)", "None of the above"},
            {"In adjacency list representation, space is saved for sparse graphs", "DFS and BSF can be done in O(V + E) time for adjacency list representation whereas O(V^2) time for adjacency matrix representation", "Adding a vertex in adjacency list representation is easier than adjacency matrix representation", "All of the above"},
            {"There must exist a vertex w adjacent to both u and n in G", "There must exist a vertex w whose removal disconnects u and n in G", "There must exist a cycle in G containing u and n", "There must exist a cycle in G containing u and all its neighbours in G"},
            {"k", "k + 1", "n - k", "n - k - 1"}
    };

    private int mCorrectAnswers[] = {3, 2, 3, 3, 2};

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