package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Arrays Implementation Methods\n2. In-depth knowledge of Sorting Algorithms";

    private String mQuestions[] = {
            "A program P reads in 500 integers in the range [0,100] representing the scores of 500 students. It then prints the frequency of each score above 50. what would be the best way for P to store the frequencies?\n",
            "Suppose you are given an array s[1...n] and a procedure reverse (s,i,j) which reverse the order of elements in s between positions i and j (both inclusive). What does the following sequence do, where 1 < k <= n:\n" +
                    "\treverse (s, 1, k);\n" +
                    "\treverse (s, k + 1, n);\n" +
                    "\treverse (s, 1, n);\n",

            "In a compact single dimensional array representation for lower triangular matrices (i.e all the elements above the diagonal are zero) of size  n x n , non-zero elements (i.e elements of the lower triangle) of each row are stored one after another, starting from the first row, the index of the  (i,j)th element of the lower triangular matrix in this new representation is:\n",
            "The minimum number of comparisons required to determine if an integer appears more than n/2 times in a sorted array of n integers is\n",
            "Consider a sorted array of n numbers. What would be the time complexity of the best known algorithm to find a pair a and b such that |a-b| = k , k being a positive integer.\n"
    };

    private String mChoices[][] = {
            {"An array of 50 numbers", "An array of 100 numbers", "An array of 500 numbers", "A dynamically allocated array of 550 numbers"},
            {"Leaves s unchanged", "Reverses all elements of s", "Rotates s left by k positions", "None of the above"},
            {"i + j", "i + j -1", "j + [i(i-1)/2]", "i + [j(j-1)/2]"},
            {"O(n)", "O(logn)", "O(n^2)", "O(nlogn)"},
            {"O(n)", "O(nlogn)", "O(logn)", "O(n^2)"}
    };

    private int mCorrectAnswers[] = {0, 2, 2, 1, 0};

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
