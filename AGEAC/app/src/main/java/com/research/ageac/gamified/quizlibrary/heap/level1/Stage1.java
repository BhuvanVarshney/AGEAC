package com.research.ageac.gamified.quizlibrary.heap.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Properties of Heap";

    private String mQuestions[] = {
            "Heap satisfies properties of BST\n",
            "In a max-heap, element with the greatest key is always in the which node?\n",
            "In a max-heap, element with the smallest key is always in the which node?\n",
            "In a min-heap, element with the greatest key is always in the which node?\n",
            "In a min-heap, element with the smallest key is always in the which node?\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"Root Node", "Leaf Node"},
            {"Root Node", "Leaf Node"},
            {"Root Node", "Leaf Node"},
            {"Root Node", "Leaf Node"}
    };

    private int mCorrectAnswers[] = {1, 0, 1, 1, 0};

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