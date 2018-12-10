package com.research.ageac.gamified.quizlibrary.array.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Basics of 2-D array\n2. Address Computation\3. Searching";

    private String mQuestions[] = {
            "Consider the following declaration of a two dimensional array in C:\n" +
                    "\tchar a[100][100];\n" +
                    "Assuming that the main memory is byte-addressable and that the array is stored starting from memory address 0, the address of a [40][50] is \n",

            "Let A be a two-dimensional array declared as follows: \n" +
                    "      A: array [1 …. 10] [1 …… 15] of integer; \n" +
                    "Assuming that each integer takes one memory location, the array is stored in row-major order and the first element of the array is stored at location 100, what is the address of the element A[i][j]?\n",

            "An n*n matrix V is defined as follows\n" +
                    "V[i,j] = i-j for all i,j, 1<=i<=n;1<=j<=n; \n" +
                    "The sum of the elements of the array V is\n",

            "In _______, search start at the beginning of the list and check every element in the list.\n",

            "State True or False.\n" +
                    "i) Binary search is used for searching in a sorted array.\n" +
                    "Ii) The time complexity of binary search is O(logn).\n"
    };

    private String mChoices[][] = {
            {"4040", "4050", "5040", "5050"},
            {"15i + j + 84", "15j + i + 84", "10i + j + 89", "10j + i + 89"},
            {"0", "n-1", "(n^2)-3n+2", "(n^2)(n+1)/2"},
            {"Linear search", "Binary search", "Hash Search", "Binary Tree search"},
            {"True, False", "False, True", "False, False", "True, True"}
    };

    private int mCorrectAnswers[] = {1, 0, 0, 0, 3};

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