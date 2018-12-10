package com.research.ageac.gamified.quizlibrary.array.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Sorting";

    private String mQuestions[] = {
            "What is the best time complexity of bubble sort?\n",
            "Assume that we use Bubble Sort to sort n distinct elements in ascending order. When does the best case of Bubble Sort occur?\n",
            "Which of the following sorting algorithms in its typical implementation gives best performance when applied on an array which is sorted or almost sorted (maximum 1 or two elements are misplaced).\n",
            "Which of the following operations is not O(1) for an array of sorted data. You may assume that array elements are distinct.\n",
            "The auxiliary space of insertion sort is O(1), what does O(1) mean ?\n"
    };

    private String mChoices[][] = {
            {"n^2", "nlogn", "n", "n(logn)^2"},
            {"When elements are sorted in ascending order", "When elements are sorted in descending order", "When elements are not sorted by any order", "There is no best case for Bubble Sort. It always takes O(n^2) time"},
            {"Quick Sort", "Heap Sort", "Merge Sort", "Insertion Sort"},
            {"Find the ith largest element", "Delete an element", "Find the ith smallest element", "All of the above"},
            {"The memory (space) required to process the data is not constant.", "It means the amount of extra memory Insertion Sort consumes doesn't depend on the input. The algorithm should use the same amount of memory for all inputs.", "It takes only 1 kb of memory", "It is the speed at which the elements are traversed."}
    };

    private int mCorrectAnswers[] = {2, 0, 3, 1, 1};

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
