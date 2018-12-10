package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Binary Tree";

    private String mQuestions[] = {
            "A full binary tree with 2n+1 nodes contain\n",
            "A full binary tree with n leaves contains\n",
            "The number of leaf nodes in a perfect binary tree of depth d is\n",
            "The height of a binary tree is the maximum number of edges in any root to leaf path. The maximum number of nodes in a binary tree of height h is\n",
            "Depth of any node of a tree is\n"
    };

    private String mChoices[][] = {
            {"n leaf nodes", "n non-leaf nodes", "n-1 non-leaf nodes", "n-1 leaf nodes"},
            {"n internal nodes", "log2n internal nodes", "n - 1 internal nodes", "2n - 1 internal nodes"},
            {"2^d", "2^(d–1)+1", "2^(d+1)+1", "2^d+1"},
            {"2^h -1", "2^(h-1) – 1", "2^(h+1) -1", "2^h+1"},
            {"Height of its left subtree minus height of its right subtree", "Height of its right subtree minus height of its left subtree", "Its distance from the root", "None of these"}
    };

    private int mCorrectAnswers[] = {1, 2, 0, 2, 2};

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