package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage7 extends MCQBaseClass {
    private String prerequisites = "1. Sorting Algorithms\n2. Searching Algorithms";

    private String mQuestions[] = {
            "Which sorting algorithms is most efficient to sort string consisting of ASCII characters?\n",

            "Consider a situation where swap operation is very costly. Which of the following sorting algorithms should be preferred so that the number of swap operations are minimized in general?\n",

            "Consider the following algorithm for searching for a given number x in an unsorted array A[1.....n] having n distinct values:\n" +
                    "\n" +
                    "   1. Choose an i uniformly at random from 1..... n;\n" +
                    "   2. If A[i] = x then Stop else Goto 1;\n" +
                    "\n" +
                    "Assuming that x is present in A, what is the expected number of comparisons made by the algorithm before it terminates?\n",

            "Suppose we have a O(n) time algorithm that finds median of an unsorted array. Now consider a QuickSort implementation where we first find median using the above algorithm, then use median as pivot. What will be the worst case time complexity of this modified QuickSort.\n",

            "Given an unsorted array. The array has this property that every element in array is at most k distance from its position in sorted array where k is a positive integer smaller than size of array. Which sorting algorithm can be easily modified for sorting this array and what is the obtainable time complexity?\n"
    };

    private String mChoices[][] = {
            {"Quick Sort", "Heap Sort", "Merge Sort", "Counting Sort"},
            {"Heap Sort", "Selection Sort", "Insertion Sort", "Merge Sort"},
            {"n", "n - 1", "2n", "n/2"},
            {"O(n^2 Logn)", "O(n^2)", "O(n Logn Logn)", "O(nLogn)"},
            {"Insertion Sort with time complexity O(kn)", "Heap Sort with time complexity O(nLogk)", "Quick Sort with time complexity O(kLogk)", "Merge Sort with time complexity O(kLogk)"}
    };

    private int mCorrectAnswers[] = {3, 1, 0, 3, 1};

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
