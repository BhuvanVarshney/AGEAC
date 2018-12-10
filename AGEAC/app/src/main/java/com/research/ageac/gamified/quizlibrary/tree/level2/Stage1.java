package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Types of Trees\n2. Basics of Implementation of tree";

    private String mQuestions[] = {
            "A tree which contain cycles is called\n",
            "Which of the following is a true about Binary Trees\n",
            "In array representation of binary tree the right child of root will be at index\n",
            "A binary tree of depth “d” is an almost complete binary tree if\n",
            "How many nodes in a tree with n nodes have no ancestors\n"
    };

    private String mChoices[][] = {
            {"Cyclic tree", "AVL Tree", "Red-Black Tree", "None of the above"},
            {"Every complete binary tree is also a full binary tree", "Every full binary tree is also a complete binary tree", "No binary tree is both complete and full", "None of the above"},
            {"2", "3", "1", "0"},
            {"Each leaf in the tree is either at level “d” or at level “d–1”", "For any node “n” in the tree with a right descendent at level “d” all the left descendents of “n” that are leaves, are also at level “d”", "Both (A) & (B) combined", "None of the above"},
            {"0", "1", "2", "n"}
    };

    private int mCorrectAnswers[] = {3, 3, 0, 2, 1};

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