package com.research.ageac.gamified.quizlibrary.bst.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary Tree";

    private String mQuestions[] = {
            "Postorder traversal of a given binary search tree, T produces the following sequence of keys\n" +
                    "\n10, 9, 23, 22, 27, 25, 15, 50, 95, 60, 40, 29\n" +
                    "\nWhich one of the following sequences of keys can be the result of an in-order traversal of the tree T?\n",
            "In order to get the information stored in a Binary Search Tree in the descending order, one should traverse it in which of the following order?\n",
            "If a node in a BST has two children, then its inorder predecessor has\n",
            "If a node in a BST has two children, then its inorder successor has\n",
            "Which of the following traversals is sufficient to construct BST from given traversals\n\n\t1) Inorder\n\t2) Preorder\n\t3) Postorder\n"
    };

    private String mChoices[][] = {
            {"9, 10, 15, 22, 40, 50, 60, 95, 23, 25, 27, 29", "29, 15, 9, 10, 25, 22, 23, 27, 40, 60, 50, 95", "9, 10, 15, 22, 23, 25, 27, 29, 40, 50, 60, 95", "95, 50, 60, 40, 27, 23, 22, 25, 10, 9, 15, 29"},
            {"left, root, right", "root, left, right", " right, left, root", "right, root, left"},
            {"no left child", "no right child", "two child", "no child"},
            {"no child", "no left child", "no right child", "two child"},
            {"1 and 3", "2 and 3", "Either 2 or 3 is sufficient", "Any one of the given three traversals is sufficient"}
    };

    private int mCorrectAnswers[] = {2, 3, 1, 1, 2};

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