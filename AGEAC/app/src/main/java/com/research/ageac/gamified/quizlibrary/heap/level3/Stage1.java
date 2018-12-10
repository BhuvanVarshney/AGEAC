package com.research.ageac.gamified.quizlibrary.heap.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Heap\n2. Heap Operations";

    private String mQuestions[] = {
            "Consider a binary max-heap implemented using an array. Which one of the following array represents a binary max-heap?\n",
            "Heap can be implemented using array because of the similar resemblance to _____\n",
            "The worst time complexity to search an element in binary heap with N elements is\n",
            "The time complexity to delete minimum element in min-heap with N elements is\n",
            "In a heap with n(n>>>4) elements with the smallest element at the root, the 4th smallest element can be found in time\n"
    };

    private String mChoices[][] = {
            {"25, 12, 16, 13, 10, 8, 14", "25, 14, 13, 16, 10, 8, 12", "25, 14, 12, 13, 10, 8, 16", "25, 14, 16, 13, 10, 8, 12"},
            {"Binary Tree", "Binary Search Tree", "Complete Binary Tree", "Full Binary Tree"},
            {"O(logN)", "O(N^2)", "O(N)", "O(NlogN)"},
            {"O(logN)", "O(1)", "O(N)", "O(NlogN)"},
            {"O(nlogn)", "O(n)", "O(logn)", "O(1)"}
    };

    private int mCorrectAnswers[] = {3, 2, 2, 0, 3};

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