package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage9 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary tree\n2. Searching in Tree\n3. Types of Binary Tree";

    private String mQuestions[] = {
            "Let LASTPOST, LASTIN and LASTPRE denote the last vertex visited in a postorder, inorder and preorder traversal. Respectively, of a complete binary tree. Which of the following is always true?\n",
            "If a problem is to reach to a node that is more likely to closer to root, ____ will be efficient\n",
            "If a problem is to reach to a node that is closer to leaves, ____ will be efficient\n",
            "The maximum number of binary trees that can be formed with three unlabelled nodes is\n",
            "Performance wise, a degenerate tree will behave like ____ data structure\n"
    };

    private String mChoices[][] = {
            {"LASTIN = LASTPOST", "LASTIN = LASTPRE", "LASTPRE = LASTPOST", "None of the above"},
            {"Breadth First Search", "Depth First Search", "Any tree traversal procedure", "None of the above"},
            {"Depth First Search", "Breadth First Search", "Any tree traversal procedure", "None of the above"},
            {"1", "3", "4", "5"},
            {"Array", "Linked List", "Binary Search Tree", "None of the above"}
    };

    private int mCorrectAnswers[] = {3, 0, 0, 3, 1};

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