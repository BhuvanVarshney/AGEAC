package com.research.ageac.gamified.quizlibrary.linkedlist.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {
    private String prerequisites = "1. Linked List implementation";

    private String mQuestions[] = {
            "Linked list can be implemented using arrays\n",
            "It is much easy to insert and delete elements in Array in comparison to Linked List\n",
            "Random access is not allowed in a typical implementation of Linked Lists\n",
            "Searching for an element is more efficient in doubly linked list than singly linked list\n",
            "Finding the predecessor takes O(1) time in case of singly linked list, given the position of the element\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 1, 1};

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