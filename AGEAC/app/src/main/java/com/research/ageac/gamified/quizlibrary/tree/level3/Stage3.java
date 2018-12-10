package com.research.ageac.gamified.quizlibrary.tree.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Basics of Tree";

    private String mQuestions[] = {
            "A complete n-ary tree is one in which every node has 0 or n sons. If x is the number of internal nodes of a complete n-ary tree, the number of leaves in it is given by\n",
            "A scheme for storing binary trees in an array X is as follows. Indexing of X starts at 1 instead of 0. the root is stored at X[1]. For a node stored at X[i], the left child, if any, is stored in X[2i] and the right child, if any, in X[2i+1]. To be able to store any binary tree on n vertices the minimum size of X should be\n",
            "The number of leaf nodes in a rooted tree of n nodes, with each node having 0 or 3 children is\n",
            "In a complete k-ary tree, every internal node has exactly k children or no child. The number of leaves in such a tree with n internal nodes is\n",
            "In a binary tree with n nodes, every node has an odd number of descendants. Every node is considered to be its own descendant. What is the number of nodes in the tree that have exactly one child?\n"
    };

    private String mChoices[][] = {
            {"xn - 1", "xn + 1", "x(n – 1) +1", "x(n+1)"},
            {"log^2n", "n", "2n + 1", "2^n — 1"},
            {"n/2", "(n-1)/3", "(n-1)/2", "(2n+1)/3"},
            {"nk", "(n – 1)k + 1", "n(k – 1) + 1", "n(k – 1)"},
            {"0", "1", "(n-1)/2", "n-1"}
    };

    private int mCorrectAnswers[] = {2, 3, 3, 2, 0};

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