package com.research.ageac.gamified.quizlibrary.heap.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Heap Operations";

    private String mQuestions[] = {
            "Suppose we are sorting an array of eight integers using heapsort, and we have just finished some heapify (either maxheapify or minheapify) operations. The array now looks like this:\n\n16 14 15 10 12 27 28\n\nHow many heapify operations have been performed on root of heap?\n",
            "Consider a max heap, represented by the array:\n\n40, 30, 20, 10, 15, 16, 17, 8, 4\n\nNow consider that a value 35 is inserted into this heap. After insertion, the new heap is\n",
            "A complete binary min-heap is made by including each integer in [1, 1023] exactly once. The depth of a node in the heap is the length of the path from the root of the heap to that node. Thus, the root is at depth 0. The maximum depth at which integer 9 can appear is\n",
            "The minimum number of interchanges needed to convert the array\n\n89, 19, 40, 17, 12, 10, 2, 5, 7, 11, 6, 9, 70\n\ninto a heap with the maximum element at the root is\n",
            "Consider the following array of elements.\n\n89, 19, 50, 17, 12, 15, 2, 5, 7, 11, 6, 9, 100\n\nThe minimum number of interchanges needed to convert it into a max-heap is\n"
    };

    private String mChoices[][] = {
            {"0", "1", "2", "3"},
            {"40, 30, 20, 10, 15, 16, 17, 8, 4, 35", "40, 35, 20, 10, 30, 16, 17, 8, 4, 15", "40, 30, 20, 10, 35, 16, 17, 8, 4, 15", "40, 35, 20, 10, 15, 16, 17, 8, 4, 30"},
            {"6", "7", "8", "9"},
            {"0", "1", "2", "3"},
            {"4", "5", "2", "3"}
    };

    private int mCorrectAnswers[] = {2, 1, 2, 2, 3};

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