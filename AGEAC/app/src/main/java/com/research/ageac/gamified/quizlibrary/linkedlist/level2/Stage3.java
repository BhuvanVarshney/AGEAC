package com.research.ageac.gamified.quizlibrary.linkedlist.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {
    private String prerequisites = "1. Basics of singly linked list implementation\n2. Basics of circular linked list implementation";

    private String mQuestions[] = {
            "Which among the following data structures is best suited for storing and performing arithmetic operations on very large numbers ( eg. numbers that cannot be stored in long long int)\n",
            "Let P be a singly linked list. Let Q be the pointer to an intermediate node x in the list. What is the worst-case time complexity to delete the predecessor of node x from the list(Given x is not the first or second element in the list)?\n",
            "A variant of the linked list in which none of the node contains NULL pointer is?\n",
            "In a Circular linked list, insertion of a record involves the modification of\n",
            "A circular linked list can be used for\n"
    };

    private String mChoices[][] = {
            {"Array", "Binary Tree", "Linked List", "Hashing"},
            {"O(n)", "O(1)", "O(logn)", "O(loglogn)"},
            {"Singly linked list", "Doubly linked list", "Circular linked list", "None of the above"},
            {"1 Pointer", "2 Pointers", "3 Pointers", "None of the above"},
            {"Stack", "Queue", "Both Stack & Queue", "Neither Stack nor Queue"}
    };

    private int mCorrectAnswers[] = {2, 0, 2, 1, 2};

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