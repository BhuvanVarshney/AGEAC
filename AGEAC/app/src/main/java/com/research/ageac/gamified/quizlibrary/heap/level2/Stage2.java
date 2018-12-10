package com.research.ageac.gamified.quizlibrary.heap.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Basics of Heap\n2. Application of Heap";

    private String mQuestions[] = {
            "Heap is commonly implemented using\n",
            "Consider any array representation of an n element binary heap where the elements are stored from index 1 to index n of the array. For the element stored at index i of the array (i <= n), the index of the parent is\n",
            "What is the time complexity of Build Heap operation. Build Heap is used to build a max(or min) binary heap from a given array. Build Heap is used in Heap Sort as a first step for sorting\n",
            "In a binary max heap containing n numbers, the smallest element can be found in ____ time\n",
            "A heap allows a very efficient implementation of a _______\n"
    };

    private String mChoices[][] = {
            {"Stacks", "Queues", "Arrays", "None of the above"},
            {"i - 1", "floor(i/2)", "ceiling(i/2)", "(i+1)/2"},
            {"O(nLogn)", "O(n^2)", "O(Logn)", "O(n)"},
            {"O(n)", "O(logn)", "O(loglogn)", "O(1)"},
            {"Stack", "Tree", "Priority Queue", "All of the above"}
    };

    private int mCorrectAnswers[] = {2, 1, 3, 0, 2};

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