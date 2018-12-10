package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage5 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary Tree";

    private String mQuestions[] = {
            "Which of the following ways is a pre-order traversal\n",
            "Which of the following ways is an in-order traversal?\n",
            "Which of the following ways is a post-order traversal?\n",
            "What is common in three different types of traversals (Inorder, Preorder and Postorder)?\n",
            "____ gives elements in ascending order of a Binary Tree\n"
    };

    private String mChoices[][] = {
            {"Root->left sub tree-> right sub tree", "Root->right sub tree-> left sub tree", "right sub tree-> left sub tree->Root", "left sub tree-> right sub tree->Root"},
            {"right sub tree-> left sub tree->Root", "Root->left sub tree-> right sub tree", "Root->right sub tree-> left sub tree", "left sub tree-> Root->right sub tree"},
            {"Root->left sub tree-> right sub tree", "right sub tree-> left sub tree->Root", "left sub tree-> right sub tree->Root", "left sub tree-> Root->right sub tree"},
            {"Root is visited before right subtree", "Left subtree is always visited before right subtree", "Root is visited after left subtree", "None of the above"},
            {"Post order traversal", "Inorder traversal", "Preorder traversal", "None of the above"}
    };

    private int mCorrectAnswers[] = {0, 3, 2, 1, 3};

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