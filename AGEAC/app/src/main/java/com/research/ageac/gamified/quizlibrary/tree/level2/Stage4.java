package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Binary Tree\n2. Searching in Binary Tree";

    private String mQuestions[] = {
            "Let T be a tree with 10 vertices. The sum of the degrees of all the vertices in T is\n",
            "A binary tree T has 20 leaves. The number of nodes in T having two children is\n",
            "A binary tree in which every non-leaf node has non-empty left and right sub trees is called a strictly binary tree. Such a tree with 10 leaves\n",
            "The time required in best case for search operation in binary tree is\n",
            "The time required in worst case for search operation in binary tree is\n"
    };

    private String mChoices[][] = {
            {"18", "19", "20", "21"},
            {"18", "19", "17", "Any number between 10 and 20"},
            {"Has 19 nodes", "Has 16 nodes", "Has 15 nodes", "None of the above"},
            {"O(logn)", "O(1)", "O(n^2)", "O(n)"},
            {"O(n)", "O(1)", "O(logn)", "O(n^2)"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 1, 0};

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