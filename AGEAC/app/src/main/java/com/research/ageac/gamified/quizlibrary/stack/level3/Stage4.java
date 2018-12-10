package com.research.ageac.gamified.quizlibrary.stack.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage4 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Stack";

    private String mQuestions[] = {
            "Following is C like pseudo code of a function that takes a number as an argument, and uses a stack S to do processing.\n\n" +
                    "void fun(int n)\n" +
                    "{\n" +
                    "    Stack S;  // Say it creates an empty stack S\n" +
                    "    while (n > 0)\n" +
                    "    {\n" +
                    "      // This line pushes the value of n%2 to stack S\n" +
                    "      push(&S, n%2);\n" +
                    " \n" +
                    "      n = n/2;\n" +
                    "    }\n" +
                    " \n" +
                    "    // Run while Stack S is not empty\n" +
                    "    while (!isEmpty(&S))\n" +
                    "      printf(\"%d \", pop(&S)); // pop an element from S and print it\n" +
                    "}\n\n" +
                    "What does the above function do in general?\n",

            "Following is an incorrect pseudocode for the algorithm which is supposed to determine whether a sequence of parentheses is balanced:\n\n" +
                    "declare a character stack \n" +
                    "while ( more input is available)\n" +
                    "{\n" +
                    "   read a character\n" +
                    "   if ( the character is a '(' ) \n" +
                    "      push it on the stack\n" +
                    "   else if ( the character is a ')' and the stack is not empty )\n" +
                    "      pop a character off the stack\n" +
                    "   else\n" +
                    "      print \"unbalanced\" and exit\n" +
                    " }\n" +
                    " print \"balanced\"\n\n" +
                    "Which of these unbalanced sequences does the above code think is balanced?\n",

            "A single array A[1..MAXSIZE] is used to implement two stacks. The two stacks grow from opposite ends of the array. Variables top1 and top2 (topl< top 2) point to the location of the topmost element in each of the stacks. If the space is to be used efficiently, the condition for “stack full” is\n",
            "Let S be a stack of size n >= 1. Starting with the empty stack, suppose we push the first n natural numbers in sequence, and then perform n pop operations. Assume that Push and Pop operation take X seconds each, and Y seconds elapse between the end of one such stack operation and the start of the next operation. For m >= 1, define the stack-life of m as the time elapsed from the end of Push(m) to the start of the pop operation that removes m from S. The average stack-life of an element of this stack is\n",
            "Consider n elements that are equally distributed in k stacks. In each stack, elements of it are arranged in ascending order (min is at the top in each of the stack and then increasing downwards). Given a queue of size n in which we have to put all n elements in increasing order. What will be the time complexity of the best known algorithm?\n"
    };

    private String mChoices[][] = {
            {"Prints binary representation of n in reverse order", "Prints binary representation of n", "Prints the value of Logn", "Prints the value of Logn in reverse order"},
            {"((())", "())(()", "(()()))", "(()))()"},
            {"(top1 = MAXSIZE/2) and (top2 = MAXSIZE/2+1)", "top1 + top2 = MAXSIZE", "(top1= MAXSIZE/2) or (top2 = MAXSIZE)", "top1= top2 -1"},
            {"n(X+ Y)", "3Y + 2X", "n(X + Y)-X", "Y + 2X"},
            {"O(n logk)", "O(nk)", "O(n^2)", "O(k^2)"}
    };

    private int mCorrectAnswers[] = {1, 0, 3, 2, 0};

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