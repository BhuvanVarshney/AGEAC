package com.research.ageac.gamified.quizlibrary.array.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {

    private String prerequisites = "1. Sorting Algorithms";

    private String mQuestions[] = {
            "Which of the following is not an in-place algorithm?\n",
            "A sorting technique is called stable if\n",
            "Which of the following is not a stable sorting algorithm in its typical implementation\n",
            "Which of the following sorting algorithms has the lowest worst-case complexity?\n",
            "Which of the following is true about merge sort?\n"
    };

    private String mChoices[][] = {
            {"Insertion Sort", "Selection Sort", "Merge Sort", "Heap Sort"},
            {"It takes O(nlog n)time", "It maintains the relative order of occurrence of non-distinct elements", "It uses divide and conquer paradigm", "It takes O(n) space"},
            {"Insertion Sort", "Merge Sort", "Bubble Sort", "Quick Sort"},
            {"Merge Sort", "Bubble Sort", "Quick Sort", "Selection Sort"},
            {"Merge Sort works better than quick sort if data is accessed from slow sequential memory", "Merge Sort is stable sort by nature", "Merge sort outperforms heap sort in most of the practical situations", "All of the above"}
    };

    private int mCorrectAnswers[] = {2, 1, 3, 0, 3};

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
