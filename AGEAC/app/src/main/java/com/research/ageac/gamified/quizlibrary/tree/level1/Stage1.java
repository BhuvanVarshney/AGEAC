package com.research.ageac.gamified.quizlibrary.tree.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Basics of Tree";

    private String mQuestions[] = {
            "Tree is ______ data type?\n",
            "A tree can contain cycles.\n",
            "To represent hierarchical relationship between elements, which data structure is suitable\n",
            "An empty tree is also a binary tree\n",
            "Every binary tree is either complete or full\n"
    };

    private String mChoices[][] = {
            {"Linear", "Non-Linear"},
            {"True", "False"},
            {"Tree", "Graph"},
            {"True", "False"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {1, 1, 0, 0, 1};

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
