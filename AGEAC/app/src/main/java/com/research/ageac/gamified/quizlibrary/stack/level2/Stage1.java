package com.research.ageac.gamified.quizlibrary.stack.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Basics of recursion and iteration";

    private String mQuestions[] = {
            "Recursion uses more memory space than iteration because\n",
            "What about recursion is true in comparison with iteration?\n",
            "When a function is recursively called, all auto variables\n",
            "The number of recursive calls is limited to the size of the\n",
            "If there is no base criteria in a recursive program, the program will\n"
    };

    private String mChoices[][] = {
            {"it uses stack instead of queue", "every recursive call has to be stored", "both A & B are true", "None of the above are true"},
            {"very expensive in terms of memory", "low performance", "every recursive program can be written with iteration too", "all of the above"},
            {"Are initialized during each execution of the function", "Are retained from the last execution", "Are maintained in a queue", "None of these"},
            {"Primary Memory", "Stack Area", "Secondary Memory", "None of the above"},
            {"not be executed", "execute infinitely", "ultimately run into stack overflow", "obtain progressive approach"}
    };

    private int mCorrectAnswers[] = {1, 3, 0, 1, 2};

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
