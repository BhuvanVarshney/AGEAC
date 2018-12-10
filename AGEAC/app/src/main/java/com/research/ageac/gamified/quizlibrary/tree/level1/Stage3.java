package com.research.ageac.gamified.quizlibrary.tree.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage3 extends TrueFalseBaseClass {

    private String prerequisites = "1. Types of Binary Trees";

    private String mQuestions[] = {
            "A perfect binary tree will have all its leaf nodes at the same level\n",
            "A perfect tree will have ___ child nodes for every internal node\n",
            "A Strict(Full) Binary Tree will have maximum number of nodes for a given height h\n",
            "No binary tree is both complete and full\n",
            "AVL Tree is a special case of ____\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"Either 0 or 2", "Exactly 2"},
            {"True", "False"},
            {"True", "False"},
            {"Complete Binary Tree", "Binary Search Tree"}
    };

    private int mCorrectAnswers[] = {0, 1, 1, 1, 1};

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