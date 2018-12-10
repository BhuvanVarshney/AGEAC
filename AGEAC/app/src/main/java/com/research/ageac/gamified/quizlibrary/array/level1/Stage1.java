package com.research.ageac.gamified.quizlibrary.array.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Searching in array\n2. Sorting in Array";

    private String questions[] = {
            "The worst case running time of Insertion sort is\n",
            "The worst case running time of Merge sort is\n",
            "The worst case running times of Quick sort is\n",
            "Binary search is used for searching in a sorted array.\n",
            "The time complexity of binary search is O(n)\n"
    };

    private String choices[][] = {
            {"O(n^2)", "O(n)"},
            {"O(n^2)", "O(nlogn)"},
            {"O(nlogn)", "O(n^2)"},
            {"True", "False"},
            {"True", "False"}
    };

    private int correctAnswers[] = {0, 1, 1, 0, 1};

    public String getPrerequisites() {
        return prerequisites;
    }

    public int getQuestionsCount() {
        return questions.length;
    }

    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice1 = choices[a][0];
        return choice1;
    }

    public String getChoice2(int a) {
        String choice2 = choices[a][1];
        return choice2;
    }

    public int getCorrectAnswer(int a) {
        return correctAnswers[a];
    }
}

