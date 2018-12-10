package com.research.ageac.gamified.quizlibrary.array.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage5 extends MCQBaseClass {
    private String prerequisites = "1. In-depth knowledge of Sorting Algorithms";

    private String mQuestions[] = {
            "Randomized quicksort is an extension of quicksort where the pivot is chosen randomly. What is the worst case complexity of sorting n numbers using randomized quicksort?\n",

            "Which of the following changes to typical QuickSort improves its performance on average and are generally done in practice.\n" +
                    "\n" +
                    "1) Randomly picking up to make worst case less likely to occur.\n" +
                    "2) Calling insertion sort for small sized arrays to reduce recursive calls.\n" +
                    "3) QuickSort is tail recursive, so tail call optimizations can be done.\n" +
                    "4) A linear time median searching algorithm is used to pick the median, so that the worst case time reduces to O(nLogn)\n",

            "Assume that a mergesort algorithm in the worst case takes 30 seconds for an input of size 64. Which of the following most closely approximates the maximum input size of a problem that can be solved in 6 minutes?\n",

            "Assume that the algorithms considered here sort the input sequences in ascending order. If the input is already in ascending order, which of the following are TRUE ?\n" +
                    "\n" +
                    "\tI.   Quicksort runs in Θ(n^2) time\n" +
                    "\tII.  Bubblesort runs in Θ(n^2) time\n" +
                    "\tIII. Mergesort runs in  Θ(n) time\n" +
                    "\tIV.  Insertion sort runs in  Θ(n) time\n",

            "Which is the correct order of the following algorithms with respect to their time Complexity in the best case ?\n"
    };

    private String mChoices[][] = {
            {"O(n)", "O(n Log n)", "O(n^2)", "O(n!)"},
            {"1 and 2", "2, 3, and 4", "1, 2 and 3", "2, 3 and 4"},
            {"256", "512", "1024", "2048"},
            {"I and II only", "I and III only", "II and IV only", "I and IV only"},
            {"Merge sort > Quick sort >Insertion sort > selection sort", "Insertion sort < Quick sort < Merge sort < selection sort", "Merge sort > selection sort > quick sort > insertion sort", "Merge sort > Quick sort > selection sort > insertion sort"}
    };

    private int mCorrectAnswers[] = {2, 2, 1, 3, 1};

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
