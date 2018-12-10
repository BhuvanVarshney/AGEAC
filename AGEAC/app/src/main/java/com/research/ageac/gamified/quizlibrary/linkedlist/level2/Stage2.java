package com.research.ageac.gamified.quizlibrary.linkedlist.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {
    private String prerequisites = "1. Basics of singly linked list implementation\n2. Basics of doubly linked list implementation";

    private String mQuestions[] = {
            "In the worst case, the number of comparisons needed to search a singly linked list of length n for a given element is\n",
            "What are the time complexities of finding 8th element from beginning and 8th element from end in a singly linked list? Let n be the number of nodes in linked list, given that n>>>8\n",
            "In doubly linked lists\n",
            "Which of the following operations is performed more efficiently by doubly linked list than by singly linked list\n",
            "The time required to delete a node x from a doubly linked list having n nodes is\n"
    };

    private String mChoices[][] = {
            {"logn", "n/2", "logn – 1", "n"},
            {"O(n) and O(n)", "O(1) and O(1)", "O(1) and O(n)", "O(n) and O(1)"},
            {"a pointer is maintained to store both next and previous nodes", "two pointers are maintained to store next and previous nodes", "a pointer to self is maintained for each node", "none of the above"},
            {"Deleting a node whose location in given", "Searching of an unsorted list for a given item", "Inverting a node after the node with given location", "Traversing a list to process each node"},
            {"O(n)", "O(1)", "O(log n)", "O(nlogn)"}
    };

    private int mCorrectAnswers[] = {3, 2, 1, 0, 1};

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

