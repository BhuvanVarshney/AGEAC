package com.research.ageac.gamified.quizlibrary.stack.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Methods of Implementation of Stack\n2. Application of Stacks in Evaluation of Expressions";

    private String mQuestions[] = {
            "How many queues are needed to implement a stack. Consider the situation where no other data structure like arrays, linked list is available to you\n",
            "Following sequence of operation is performed on a stack. \n\tPush(1)\n\tPush(2)\n\tPop\n\tPush(1)\n\tPush(2)\n\tPop\n\tPop\n\tPop\n\tPush(2)\n\tPop\n\n The sequences of popped out values are\n",
            "The postfix form of the following infix notation is : \n\n\t(A + B) * (C * D − E) * F\n",
            "The postfix form of \n\n\tA * B + C / D\n\nis\n",
            "What is the postfix form of the following prefix\n\n\t* + a b – c d\n"
    };

    private String mChoices[][] = {
            {"1", "2", "3", "4"},
            {"2, 2, 1, 2, 1", "2, 1, 2, 2, 1", "2, 2, 1, 1, 2", "2, 1, 1, 2, 2"},
            {"A B + C D * E − * F *", "A B + C D − E F * −  * *", "A B + C D E * − * F *", "A B C D E F * − + * *"},
            {"* A B / C D", "A B * C D / +", "A B C D + / *", "A * B C + / D"},
            {"a b + c d – *", "a b c d + * –", "a b + * c d –", "a b * + c d –"}
    };

    private int mCorrectAnswers[] = {1, 2, 0, 1, 0};

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