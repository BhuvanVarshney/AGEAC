package com.research.ageac.gamified.quizlibrary.stack.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage2 extends TrueFalseBaseClass {

    private String prerequisites = "1. Implementation of Stack";

    private String mQuestions[] = {
            "First-in-first out types of computations are efficiently supported by STACKS\n",
            "Recursions in a program are handled using Stack\n",
            "Random access of elements is possible in Stacks\n",
            "Printing the elements in the order they were pushed into stack is not possible using Stack(s)\n",
            "UNDO-REDO operation can be done by using just 1 stack\n"
    };

    private String mChoices[][] = {
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {1, 0, 1, 1, 1};

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