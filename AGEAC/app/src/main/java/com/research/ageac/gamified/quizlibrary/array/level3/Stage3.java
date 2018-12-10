package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {
    private String prerequisites = "1. In-depth knowledge of Sorting Algorithms\n2. External Sorting";

    private String mQuestions[] = {
            "Suppose we are sorting an array of eight integers using quicksort, and we have just finished the first partitioning with the array looking like this: \n\n\t2, 5, 1, 7, 9, 12, 11, 10\n\n Which statement is correct?\n",
            "Suppose we are sorting an array of eight integers using heapsort, and we have just finished some heapify (either maxheapify or minheapify) operations. The array now looks like this: \n\n" +
                    "\t16, 14, 15, 10, 12, 27, 28 \n\n" +
                    "How many heapify operations have been performed on root of heap?\n",
            "You have to sort 1 GB of data with only 100 MB of available main memory. Which sorting technique will be most appropriate?\n",
            "What will be the worst case time complexity of insertion sort if position of the data to be inserted is calculated using binary search?\n",
            "The tightest lower bound on the number of comparisons, in the worst case, for comparison-based sorting is of the order of\n"
    };

    private String mChoices[][] = {
            {"The pivot could be either the 7 or the 9", "The pivot could be the 7, but it is not the 9", "The pivot is not the 7, but it could be the 9", "Neither the 7 nor the 9 is the pivot"},
            {"1", "2", "3 or 4", "5 or 6"},
            {"Heap Sort", "Quick Sort", "External Merge Sort", "Insertion Sort"},
            {"n", "nlogn", "n^2", "n(logn)^2"},
            {"n", "n^2", "nlogn", "n(logn)^2"}
    };

    private int mCorrectAnswers[] = {0, 1, 2, 2, 2};

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
