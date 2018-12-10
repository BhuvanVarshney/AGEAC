package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Properties of Binary Tree";

    private String mQuestions[] = {
            "What is the maximum possible number of nodes at level 6 in a binary tree?\n",
            "Level of a node is distance from root to that node. Level of root is 1 The maximum number of nodes in a binary tree of level i is\n",
            "The height of a tree is the length of the longest root-to-leaf path in it. The maximum and minimum number of nodes in a binary tree of height 5 are\n",
            "In a binary tree, the number of terminal or leaf nodes is 10. The number of nodes with two children is\n",
            "A complete Binary Tree with 15 nodes contains ________ edges\n"
    };

    private String mChoices[][] = {
            {"31", "32", "64", "128"},
            {"2^(i)-1", "2^i", "2^(i+1)", "2^[(i+1)/2]"},
            {"63 and 6, respectively", "64 and 5, respectively", "32 and 6, respectively", "31 and 5, respectively"},
            {"9", "11", "15", "20"},
            {"30", "16", "15", "14"}
    };

    private int mCorrectAnswers[] = {1, 0, 0, 0, 3};

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