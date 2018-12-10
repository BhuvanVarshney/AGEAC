package com.research.ageac.gamified.quizlibrary.graph.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage6 extends MCQBaseClass {

    private String prerequisites = "1. Spanning Trees\n2. Shortest Path Algorithms";

    private String mQuestions[] = {
            "Which of the following statement(s)is / are correct regarding Bellman-Ford shortest path algorithm?\n" +
                    "\n" +
                    "P: Always finds a negative weighted cycle, if one exist s.\n" +
                    "Q: Finds whether any negative weighted cycle is reachable from the source.\n",
            "Let G be a complete undirected graph on 4 vertices, having 6 edges with weights being 1, 2, 3, 4, 5, and 6. The maximum possible weight that a minimum weight spanning tree of G can have is\n",
            "Let G be a weighted connected undirected graph with distinct positive edge weights. If every edge weight is increased by the same value, then which of the following statements is/are TRUE?\n" +
                    "\n" +
                    "P: Minimum spanning tree of G does not change\n" +
                    "Q: Shortest path between any pair of vertices does not change\n",
            "Let G be connected undirected graph of 100 vertices and 300 edges. The weight of a minimum spanning tree of G is 500. When the weight of each edge of G is increased by five, the weight of a minimum spanning tree becomes\n",
            "In a weighted graph, assume that the shortest path from a source 's' to a destination 't' is correctly calculated using a shortest path algorithm. If we multiply weight of every edge by n(n>1), then\n"
    };

    private String mChoices[][] = {
            {"P only", "Q only", "Both P and Q", "Neither P nor Q"},
            {"6", "7", "8", "9"},
            {"P only", "Q only", "Both P and Q", "Neither P nor Q"},
            {"1000", "2000", "995", "1995"},
            {"Shortest path will remain same in every case", "Shortest path may change", "Weight sum along the path will change in every case", "Both A and C"}
    };

    private int mCorrectAnswers[] = {1, 1, 0, 2, 3};

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