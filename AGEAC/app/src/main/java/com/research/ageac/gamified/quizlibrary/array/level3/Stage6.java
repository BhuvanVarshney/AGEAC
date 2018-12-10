package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage6 extends MCQBaseClass {
    private String prerequisites = "1. Sorting Algorithms\n2. Searching Algorithms";

    private String mQuestions[] = {
            "An inversion in a an array A[] is a pair (A[i], A[j]) such that A[i] > A[j] and i < j. An array will have maximum number of inversions if it is\n",
            "An element in an array X is called a leader if it is greater than all elements to the right of it in X. The best algorithm to find all leaders in an array\n",
            "Consider the following C function in which size is the number of elements in the array E: The value returned by the function MyX is the\n" +
                    "\n" +
                    "int MyX(int *E, unsigned int size)\n" +
                    "{\n" +
                    "    int Y = 0, Z, i, j, k;\n" +
                    "    for(i = 0; i < size; i++)\n" +
                    "       Y = Y + E[i];\n" +
                    "    for(i = 0; i < size; i++)\n" +
                    "       for(j = i; j < size; j++)\n" +
                    "       {\n" +
                    "           Z = 0;\n" +
                    "           for(k = i; k <= j; k++)\n" +
                    "              Z = Z + E[k];\n" +
                    "           if (Z > Y)\n" +
                    "              Y = Z;\n" +
                    "       }\n" +
                    "    return Y;\n" +
                    "}\n",
            "Given an array that represents elements of arithmetic progression in order. It is also given that one element is missing in the progression, the worst case time complexity to find the missing element efficiently is\n",
            "Let swap() be a function that swaps two elements using their addresses. Consider the following C function.\n" +
                    "\n" +
                    "void fun(int arr[], int n)\n" +
                    "{\n" +
                    "    for (int i = 0; i < n; i+=2)\n" +
                    "    {\n" +
                    "        if (i>0 && arr[i-1] > arr[i] )\n" +
                    "            swap(&arr[i], &arr[i-1]); \n" +
                    "        if (i<n-1 && arr[i] < arr[i+1] )\n" +
                    "            swap(&arr[i], &arr[i + 1]);\n" +
                    "    }\n" +
                    "}\n\n" +
                    "If an array {10, 20, 30, 40, 50, 60, 70, 80} is passed to the function, the array is changed to\n"
    };

    private String mChoices[][] = {
            {"Sorted in increasing order", "Sorted in decreasing order", "Sorted in alternate fashion", "Either A or B"},
            {"Solves it in linear time using a left to right pass of the array", "Solves it in linear time using a right to left pass of the array", "Solves it using divide and conquer in time O(nlogn)", "Solves it in time O(n^2)"},
            {"maximum possible sum of elements in any sub-array of array E", "maximum element in any sub-array of array E", "sum of the maximum elements in all possible sub-arrays of array E", "sum of all the elements in the array E"},
            {"O(n)", "O(nLogn)", "O(Logn)", "O(1)"},
            {"{20, 10, 40, 30, 60, 50, 80, 70}", "{10, 30, 20, 40, 60, 50, 80, 70}", "{10, 20, 30, 40, 50, 60, 70, 80}", "{80, 70, 60, 50, 40, 30, 20, 10}"}
    };

    private int mCorrectAnswers[] = {1, 1, 0, 2, 0};

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