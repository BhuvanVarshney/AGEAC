package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. In-depth knowledge of Sorting Algorithms";

    private String mQuestions[] = {
            "Which is the best sorting algorithm to use for the elements in array are more than 1 million in general?\n",
            "Which of the below given sorting techniques has highest best-case runtime complexity.\n",
            "Quicksort is run on two inputs shown below to sort in ascending order taking first element as pivot\n" +
                    "\n" +
                    "\ti) 1, 2, 3,......., n\n" +
                    "\tii) n, n-1, n-2,......, 2, 1 \n" +
                    "\n" +
                    "Let C1 and C2 be the number of comparisons made for the inputs (i) and (ii) respectively. Then,\n",
            "Consider the C function given below. Assume that the array listA contains n (> 0) elements, sorted in ascending order.\n" +
                    "int ProcessArray(int *listA, int x, int n)\n" +
                    "{\n" +
                    "    int i, j, k;\n" +
                    "    i = 0;\n" +
                    "    j = n-1;\n" +
                    "    do\n" +
                    "    {\n" +
                    "        k = (i+j)/2;\n" +
                    "        if (x <= listA[k])\n" +
                    "            j = k-1;\n" +
                    "        if (listA[k] <= x)\n" +
                    "            i = k+1;\n" +
                    "    }\n" +
                    "    while (i <= j);\n" +
                    "    if (listA[k] == x)\n" +
                    "        return(k);\n" +
                    "    else\n" +
                    "        return -1;\n" +
                    "}\n" +
                    "Which one of the following statements about the function ProcessArray is CORRECT?\n",
            "Number of swapping, operations need to sort numbers \n\n\t8, 22, 7, 9, 31, 19, 5, 13 \n\nin ascending order using bubble sort\n"
    };

    private String mChoices[][] = {
            {"Merge sort", "Bubble sort", "Quick sort", "Insertion sort"},
            {"Quick sort", "Selection sort", "Insertion sort", "Bubble sort"},
            {"C1 < C2", "C1 > C2", "C1 = C2", "We cannot say anything for arbitrary n"},
            {"It will run into an infinite loop when x is not in listA", "It is an implementation of binary search", "It will always find the maximum element in listA", "It will return −1 even when x is present in listA"},
            {"12", "13", "14", "15"}
    };

    private int mCorrectAnswers[] = {2, 1, 2, 1, 2};

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
