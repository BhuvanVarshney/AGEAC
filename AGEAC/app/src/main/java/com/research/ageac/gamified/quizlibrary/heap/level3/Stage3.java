package com.research.ageac.gamified.quizlibrary.heap.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Min Heap Implementation\n2. Max Heap Implementation\n3. Basics of Priority Queue";

    private String mQuestions[] = {
            "A priority queue is implemented as a Max-Heap. Initially, it has 5 elements. The level-order traversal of the heap is: 10, 8, 5, 3, 2. Two new elements 1 and 7 are inserted into the heap in that order. The level-order traversal of the heap after the insertion of the elements is\n",
            "A data structure is required for storing a set of integers such that each of the following operations can be done in (log n) time, where n is the number of elements in the set.\n" +
                    "\na. Deletion of the smallest element \n" +
                    "b. Insertion of an element if it is not already present in the set\n" +
                    "\n" +
                    "Which of the following data structures can be used for this purpose?\n",
            "What will be the most efficient in finding the top 10 largest items out of 1 million items stored in file?\n",
            "Which of the following Binary Min Heap operation has the highest time complexity?\n",
            "Given two max heaps of size n each, what is the minimum possible time complexity to make a one max-heap of size from elements of two max heaps?\n"
    };

    private String mChoices[][] = {
            {"10, 8, 7, 3, 2, 1, 5", "10, 8, 7, 2, 3, 1, 5", "10, 8, 7, 1, 2, 3, 5", "10, 8, 7, 5, 3, 2, 1"},
            {"A heap can be used but not a balanced binary search tree", "A balanced binary search tree can be used but not a heap", "Both balanced binary search tree and heap can be used", "Neither balanced binary search tree nor heap can be used"},
            {"Min heap of Size 10", "Max heap of size 10", "Max heap of size 1 million", "BST"},
            {"Inserting an item under the assumption that the heap has capacity to accommodate one more item", "Merging with another heap under the assumption that the heap has capacity to accommodate items of other heap", "Deleting an item from heap", "Decreasing value of a key"},
            {"O(nLogn)", "O(nLogLogn)", "O(n)", "O(Logn)"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 1, 2};

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