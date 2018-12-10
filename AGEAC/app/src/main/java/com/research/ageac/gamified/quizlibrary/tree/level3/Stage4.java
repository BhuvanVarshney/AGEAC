package com.research.ageac.gamified.quizlibrary.tree.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {

    private String prerequisites = "1. Basics of Tree\n2. Finding the number of trees\n3. Breadth First Search";

    private String mQuestions[] = {
            "A complete n-ary tree is a tree in which each node has n children or no children. Let I be the number of internal nodes and L be the number of leaves in a complete n-ary tree. If L = 41, and I = 10, what is the value of n?\n",
            "In a binary tree, for every node the difference between the number of nodes in the left and right subtrees is at most 2. If the height of the tree is h > 0, then the minimum number of nodes in the tree is\n",
            "The maximum possible number of binary trees with 5 labelled nodes are\n",
            "A binary tree with n > 1 nodes has n1, n2 and n3 nodes of degree one, two and three respectively. n3 can be expressed as \n" +
                    "(The degree of a node here is defined as the number of its neighbors.)\n",

            "Breadth First Search (BFS) is started on a binary tree beginning from the root vertex. There is a vertex t at a distance ‘4’ from the root. If t is the n-th vertex in this BFS traversal, then the maximum possible value of n is\n"
    };

    private String mChoices[][] = {
            {"3", "4", "5", "6"},
            {"2^(h – 1)", "2^(h – 1) + 1", "2^h - 1", "2^h"},
            {"5", "42", "5040", "10080"},
            {"n1 + n2 - 1", "n1 - 2", "[((n1 + n2)/2)]", "n2 – 1"},
            {"15", "16", "31", "32"}
    };

    private int mCorrectAnswers[] = {2, 1, 2, 1, 2};

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