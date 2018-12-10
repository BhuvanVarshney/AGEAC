package com.research.ageac.gamified.quizlibrary.array.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage5 extends MCQBaseClass {

    private String prerequisites = "1. Sorting\n2. Basics of Bit Operations";

    private String mQuestions[] = {
            "The worst case running times of Insertion sort, Merge sort and Quick sort, respectively, are\n",
            "Which sorting algorithm will take least time when all elements of input array are identical? Consider typical implementations of sorting algorithms.\n",
            "What is the return value of following function for arr[] = {9, 12, 2, 11, 2, 2, 10, 9, 12, 10, 9, 11, 2} and n is size of this array.\n" +
                    "int fun(int arr[], int n)\n" +
                    "{\n" +
                    "     int x = arr[0];\n" +
                    "     for (int i = 1; i < n; i++)\n" +
                    "          x = x ^ arr[i];\n" +
                    "     return x;\n" +
                    "}\n",

            "Let A be a square matrix of size n x n. Consider the following program. What is the expected output?\n" +
                    "C = 100\n" +
                    "for i = 1 to n do\n" +
                    "      for j = 1 to n do\n" +
                    "      {\n" +
                    "          Temp = A[i][j] + C\n" +
                    "          A[i][j] = A[j][i]\n" +
                    "          A[j][i] = Temp - C\n" +
                    "      }\n" +
                    "for i = 1 to n do\n" +
                    "      for j = 1 to n do\n" +
                    "          Output(A[i][j]);\n",

            "A matrix in which number of zero elements are much higher than the number of non zero\n"
    };

    private String mChoices[][] = {
            {"Θ(n log n), Θ(n log n) and Θ(n^2)", "Θ(n^2), Θ(n^2) and Θ(n Log n)", "Θ(n^2), Θ(n log n) and Θ(n log n)", "Θ(n^2), Θ(n log n) and Θ(n^2)"},
            {"Insertion Sort", "Heap Sort", "Merge Sort", "Selection Sort"},
            {"0", "9", "12", "2"},
            {"The matrix A itself", "Transpose of matrix A", "Adding 100 to the upper diagonal elements and subtracting 100 from diagonal elements of A", "None of the above"},
            {"Scalar Matrix", "Identity Matrix", "Sparse Matrix", "None of the above"}
    };

    private int mCorrectAnswers[] = {3, 0, 1, 0, 2};

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
