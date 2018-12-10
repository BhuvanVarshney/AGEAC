package com.research.ageac.gamified.quizlibrary.tree.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage4 extends TrueFalseBaseClass {

    private String prerequisites = "1. Types of Binary Trees\n2. Basics of Trees";

    private String mQuestions[] = {
            "A binary search tree whose left subtree and right subtree differ in height by at most 1 unit is called\n",
            "The in-degree of root node is always zero\n",
            "Nodes that are not root and not leaf have out-degree\n",
            "Nodes that are not root and not leaf have in-degree\n",
            "The root node is at _____ 0\n"
    };

    private String mChoices[][] = {
            {"Red-Black Tree", "AVL Tree"},
            {"True", "False"},
            {"Exactly 1", "Atleast 1"},
            {"Exactly 1", "Atmost 1"},
            {"Level", "Height"}
    };

    private int mCorrectAnswers[] = {1, 0, 1, 0, 1};

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