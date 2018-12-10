package com.research.ageac.gamified.quizlibrary.hashing.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Basics of Hashing";

    private String mQuestions[] = {
            "A technique for direct search is\n",
            "Hashing takes ____ to search from a list of 'n' element\n",
            "A hash function takes a message of arbitrary length and generates a fixed length code\n",
            "A hash function takes a message of fixed length and generates a code of variable length\n",
            "A hash function may give the same hash value for distinct messages\n"
    };

    private String mChoices[][] = {
            {"Binary Search", "Hashing"},
            {"O(1)", "O(n)"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {1, 0, 0, 1, 0};

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