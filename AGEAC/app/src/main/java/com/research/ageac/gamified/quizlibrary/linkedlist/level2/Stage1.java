package com.research.ageac.gamified.quizlibrary.linkedlist.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {
    private String prerequisites = "1. Basics of singly linked list implementation";

    private String mQuestions[] = {
            "Linked Lists are used to implement\n",
            "Which of the following points is/are true about Linked List data structure when it is compared with array\n",
            "You are given pointers to first and last nodes of a singly linked list, which of the following operations are dependent on the length of the linked list?\n",
            "Let P be a singly linked list. Let Q be the pointer to an intermediate node x in the list. What is the worst-case time complexity of the best known algorithm to delete the node x from the list?\n",
            "Linked lists are not suitable data structures for which one of the following problems\n"
    };

    private String mChoices[][] = {
            {"Stacks", "Graphs", "Queues", "All of the above"},
            {"It is easy to insert and delete elements in Linked List", "Random access is not allowed in a typical implementation of Linked Lists", "The size of array has to be pre-decided, linked lists can change their size any time", "All of the above"},
            {"Delete the first element", "Insert a new element as a first element", "Delete the last element of the list", "Add a new element at the end of the list"},
            {"O(n)", "O((logn)^2)", "O(logn)", "O(1)"},
            {"insertion sort", "binary search", "polynomial manipulation", "radix sort"}
    };

    private int mCorrectAnswers[] = {3, 3, 2, 3, 1};

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