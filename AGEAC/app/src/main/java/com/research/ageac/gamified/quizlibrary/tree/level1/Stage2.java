package com.research.ageac.gamified.quizlibrary.tree.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage2 extends TrueFalseBaseClass {

    private String prerequisites = "1. Types of Binary Tree";

    private String mQuestions[] = {
            "A binary tree whose every node other than leaf nodes have either zero or two children is called\n",
            "A binary tree in which if all its levels except possibly the last, have the maximum number of nodes and all the nodes at the last level appear as far left as possible, is known as \n",
            "Every complete binary tree is also a full binary tree\n",
            "Every full binary tree is also a complete binary tree\n",
            "A binary tree is _____ iff total number of nodes in a binary tree of\nheight h is 2^(h+1)-1\n"
    };

    private String mChoices[][] = {
            {"Strict Binary Tree", "Perfect Binary Tree"},
            {"AVL Tree", "Complete Binary Tree"},
            {"True", "False"},
            {"True", "False"},
            {"Full Binary Tree", "Perfect Binary Tree"}
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