package com.research.ageac.gamified.quizlibrary.tree.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage7 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary Tree";

    private String mQuestions[] = {
            "The pre-order and post order traversal of a Binary Tree generates the same output. The tree can have maximum\n",
            "The post order traversal of a binary tree is DEBFCA. The root node of the tree is\n",
            "The pre order traversal of a binary tree is DEBFCA. The root node of the tree is\n",
            "Level order traversal of a rooted tree can be done by starting from the root and performing\n",
            "The array representation of a complete binary tree contains the data in sorted order. Which traversal of the tree will produce the data in sorted form?\n"
    };

    private String mChoices[][] = {
            {"3 nodes", "2 nodes", "1 node", "Any number of nodes"},
            {"D", "B", "A", "None of the above"},
            {"D", "A", "B", "None of the above"},
            {"Pre-order traversal", "In-order traversal", "Depth First Search", "Breadth First Search"},
            {"Pre-order", "In-order", "Post-order", "Level Order"}
    };

    private int mCorrectAnswers[] = {2, 2, 0, 3, 3};

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