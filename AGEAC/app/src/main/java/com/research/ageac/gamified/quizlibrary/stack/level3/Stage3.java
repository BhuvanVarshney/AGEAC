package com.research.ageac.gamified.quizlibrary.stack.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Application of Stacks in Evaluation of Expressions\n2. Implementation of Stack";

    private String mQuestions[] = {
            "The prefix of\n\n\t( A + B ) * ( C - D ) / E * F\n\nis\n",
            "The following postfix expression with single digit operands is evaluated using a stack\n\n\t8 2 3 ^ / 2 3 * + 5 1 * -\n\nThe top two elements of the stack after the first * is evaluated are\n",
            "Assume that the operators +, -, × are left associative and ^ is right associative. The order of precedence (from highest to lowest) is ^, x , +, -. The postfix expression corresponding to the infix expression\n\n\ta + b × c - d ^ e ^ f\n\nis\n",
            "The result evaluating the postfix expression\n\n\t10 5 + 60 6 / * 8 –\n\nis\n",
            "Consider the following pseudo code that uses a stack\n" +
                    "\n" +
                    "declare a stack of characters\n" +
                    "while ( there are more characters in the word to read )\n" +
                    "{\n" +
                    "   read a character\n" +
                    "   push the character on the stack\n" +
                    "}\n" +
                    "\n" +
                    "while ( the stack is not empty )\n" +
                    "{\n" +
                    "   pop a character off the stack\n" +
                    "   write the character to the screen\n" +
                    "}\n" +
                    "\n" +
                    "What is output for input \"datastructures\"?\n"
    };

    private String mChoices[][] = {
            {"/ + - A B * C D E F", "/ * + - A B C D * E F", "* / * + A B - C D E F", "* * A B + C D / E F"},
            {"6, 1", "5, 7", "3, 2", "1, 5"},
            {"a b c × + d e ^ f ^ -", "a b + c × d - e ^ f ^", "- + a × b c ^ ^ d e f", "a b c × + d e f ^ ^ -"},
            {"284", "213", "142", "71"},
            {"datastructuresdatastructures", "serutcurtsatad", "datastructures", "None of the above"}
    };

    private int mCorrectAnswers[] = {2, 0, 3, 2, 1};

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
