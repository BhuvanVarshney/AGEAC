package com.research.ageac.gamified.quizlibrary.stack.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Application of Stacks in Evaluation of Expressions";

    private String mQuestions[] = {
            "The prefix form of \n\n\tA - B / ( C * D ^ E )\n\nis\n",
            "The equivalent prefix expression for the following infix expression\n\n\t( A + B ) - ( C + D * E ) / F * G\n\nis\n",
            "The result of evaluating the postfix expression\n\n\t5, 4, 6, +, *, 4, 9, 3, /, +, *\n\nis\n",
            "The result of evaluating the following postfix expression is\n\n\t5, 7, 9, *, +, 4, 9, 3, /, +, -\n",
            "The postfix form of\n\n\tA ^ B * C - D + E/ F/ (G + H)\n\nis\n"
    };

    private String mChoices[][] = {
            {"- / * ^ A C B D E", "- A B C D * ^ D E", "- A / B * C ^ D E", "- A / B C * ^ D E"},
            {"- + A B * / + C * D E F G", "/ - + A B * + C * D E F G", "- + A B * / + C D E * F G", "- / + A B * + C D E * F G"},
            {"600", "350", "650", "588"},
            {"50", "65", "61", "69"},
            {"A B ^ C * D - E F / G H + / +", "A B ^ C D - E P / G H + / + *", "A B ^ D + E F G H + / / * +", "A B C D E F G H + / / + - * ^"}
    };

    private int mCorrectAnswers[] = {2, 0, 1, 2, 0};

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