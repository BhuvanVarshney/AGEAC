package com.research.ageac.gamified.quizlibrary.stack.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Basics of Stack";

    private String mQuestions[] = {
            "Stack is also called as\n",
            "Inserting an item into the stack is called ______ Operation\n",
            "Deletion of item form the stack is called ______ Operation\n",
            "Stack is ______ data type\n",
            "Stacks can be implemented using Array but not Linked List\n"
    };

    private String mChoices[][] = {
            {"LIFO", "FIFO"},
            {"Insert", "Push"},
            {"Pop", "Delete"},
            {"Linear", "Non-Linear"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {0, 1, 0, 0, 1};

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
