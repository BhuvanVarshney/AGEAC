package com.research.ageac.gamified.quizlibrary.bst.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Basics of BST\n2. Implementation of BST\n3. Traversal in Binary Tree";

    private String mQuestions[] = {
            "The preorder traversal sequence of a binary search tree is\n\n 30, 20, 10, 15, 25, 23, 39, 35, 42\n\nWhich one of the following is the postorder traversal sequence of the same tree?\n",
            "A binary search tree contains the value\n\n1, 2, 3, 4, 5, 6, 7, 8\n\nThe tree is traversed in pre-order and the values are printed out. Which of the following sequences is a valid output?\n",
            "We are given a set of n distinct elements and an unlabeled binary tree with n nodes. In how many ways can we populate the tree with the given set so that it becomes a binary search tree?\n",
            "Suppose that we have numbers between 1 and 100 in a binary search tree and want to search for the number 55. Which of the following sequences CANNOT be the sequence of nodes examined?\n",
            "The numbers 1, 2, .... n are inserted in a binary search tree in some order. In the resulting tree, the right subtree of the root contains p nodes. The first number to be inserted in the tree must be\n"
    };

    private String mChoices[][] = {
            {"10, 20, 15, 23, 25, 35, 42, 39, 30", "15, 10, 25, 23, 20, 42, 35, 39, 30", "15, 20, 10, 23, 25, 42, 35, 39, 30", "15, 10, 23, 25, 20, 35, 42, 39, 30"},
            {"5 3 1 2 4 7 8 6", "5 3 1 2 4 7 6 8", "5 3 1 2 6 4 8 7", "5 3 2 4 1 6 7 8"},
            {"0", "1", "n!", "None of the above"},
            {"{10, 75, 64, 43, 60, 57, 55}", "{90, 12, 68, 34, 62, 45, 55}", "{9, 85, 47, 68, 43, 57, 55}", "{79, 14, 72, 56, 16, 53, 55}"},
            {"p", "n - p", "p + 1", "n - p + 1"}
    };

    private int mCorrectAnswers[] = {3, 1, 1, 2, 1};

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