package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {
    private String prerequisites = "1. In-depth knowledge of Sorting Algorithms";

    private String mQuestions[] = {
            "In a modified merge sort, the input array is splitted at a position one-third of the length(N) of the array. What is the worst case time complexity of this merge sort?\n",
            "In quick sort, for sorting n elements, the (n/4)th smallest element is selected as pivot using an O(n) time algorithm. What is the worst case time complexity of the quick sort?\n",
            "Given an array where numbers are in range from 1 to n^6, which sorting algorithm can be used to sort these number in linear time?\n",
            "Let P be a QuickSort Program to sort numbers in ascending order using the first element as pivot. Let t1 and t2 be the number of comparisons made by P for the inputs {1, 2, 3, 4, 5} and {4, 1, 5, 3, 2} respectively. Which one of the following holds?\n",
            "You have an array of n elements. Suppose you implement quicksort by always choosing the central element of the array as the pivot. Then the tightest upper bound for the worst case performance is\n"
    };

    private String mChoices[][] = {
            {"N(logN base 3)", "N(logN base 2/3)", "N(logN base 1/3)", "N(logN base 3/2)"},
            {"O(n)", "O(nLogn)", "O(n^2)", "O(n^2 log n)"},
            {"Not possible to sort in linear time", "Radix Sort", "Counting Sort", "Quick Sort"},
            {"t1 = 5", "t1 < t2", "t1 > t2", "t1 = t2"},
            {"O(n^2)", "O(nLogn)", "Θ(nLogn)", "O(n^3)"}
    };

    private int mCorrectAnswers[] = {3, 1, 1, 2, 0};

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

