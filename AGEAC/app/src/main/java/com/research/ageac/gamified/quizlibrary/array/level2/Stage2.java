package com.research.ageac.gamified.quizlibrary.array.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {
    private String prerequisites = "1. Searching\n2. Basic implementation of Bubble Sort";

    private String mQuestions[] = {
            "The average number of key comparisons done in successful sequential search in a list of length n is\n",
            "The number of interchanges required to sort 5, 1, 6, 2, 4 in ascending order using Bubble Sort is\n",
            "A sorted file contains 16 items. Using binary search, the maximum number of comparisons to search for an item in this file is\n",
            "A characteristic of the data that binary search uses but the linear search ignores is the\n",
            "Given an array of integers with negative numbers followed by positive numbers. The index of first positive integer can be found efficiently in ____ time\n"
    };

    private String mChoices[][] = {
            {"log n", "n/2", "(n+1)/2", "(n-1)/2"},
            {"5", "6", "7", "8"},
            {"18", "8", "1", "4"},
            {"Length of the list", "Order of the elements of the list", "Maximum value in list", "Type of elements of the list"},
            {"O(n)", "O(n^2)", "O(nlogn)", "O(logn)"}
    };

    private int mCorrectAnswers[] = {2, 0, 3, 1, 3};

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