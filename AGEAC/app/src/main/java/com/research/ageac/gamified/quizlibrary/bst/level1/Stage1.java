package com.research.ageac.gamified.quizlibrary.bst.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Properties of BST\n2. Traversal in Binary Tree";

    private String mQuestions[] = {
            "The left subtree of a node contains only nodes with keys less than the node's key\n",
            "The right subtree of a node contains nodes with keys less than the node's left subtree maximum key value\n",
            "BST takes care of complete binary tree properties\n",
            "___ gives elements in ascending order of a Binary Search Tree\n",
            "Access time of a binary search tree may go worse in terms of time complexity upto\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"Inorder traversal", "Preorder traversal"},
            {"O(logn)", "O(n)"}
    };

    private int mCorrectAnswers[] = {0, 1, 1, 0, 1};

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