package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage8 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary tree\n2. Basics of AVL Trees\n3. Basics of Minimum Spanning Tree";

    private String mQuestions[] = {
            "Which of the following pairs of traversals is not sufficient to build a binary tree from the given traversals?\n",
            "The balance factor for an AVL tree is either\n",
            "What is the maximum height of any AVL-tree with 7 nodes? Assume that the height of a tree with a single node is 0\n",
            "Re-balancing of AVL tree costs\n",
            "A minimum spanning tree is a spanning tree organized so that the total _____ between nodes is minimized\n"
    };

    private String mChoices[][] = {
            {"Pre-order and In-order", "In-order and Post-order", "Pre-order and Post-order", "None of the above"},
            {"0, 1 or -1", "-2, -1 or 0", "0, 1 or 2", "None of the above"},
            {"2", "3", "4", "5"},
            {"O(1)", "O(n)", "O(logn)", "O(n^2)"},
            {"edge size", "edge height", "edge width", "edge weight"}
    };

    private int mCorrectAnswers[] = {2, 0, 1, 2, 3};

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