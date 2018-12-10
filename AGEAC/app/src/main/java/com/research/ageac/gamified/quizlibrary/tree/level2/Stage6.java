package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage6 extends MCQBaseClass {

    private String prerequisites = "1. Searching in Binary Tree";

    private String mQuestions[] = {
            "Breadth First search is used in\n",
            "Breadth First search use ___ data structure\n",
            "Depth First search use ___ data structure\n",
            "Which traversal of tree resembles the breadth first search of the graph?\n",
            "Which method can find if two vertices x & y have path between them?\n"
    };

    private String mChoices[][] = {
            {"Binary trees", "Stacks", "Graphs", "Both a and c"},
            {"Queue", "Stack", "Heap", "None of the above"},
            {"Queue", "Stack", "Heap", "None of the above"},
            {"Pre-order", "In-order", "Post-order", "Level-order"},
            {"Depth First Search", "Breadth First Search", "Both A & B", "None of the above"}
    };

    private int mCorrectAnswers[] = {3, 0, 1, 3, 2};

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