package com.research.ageac.gamified.quizlibrary.queue.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Operations on Queue\n2. Implementation of Queue using Stack\n3. Implementation of Priority Queue";

    private String mQuestions[] = {
            "Following is C like pseudo code of a function that takes a Queue as an argument, and uses a stack S to do processing.\n\n" +
                    "void fun(Queue *Q)\n" +
                    "{\n" +
                    "    Stack S;  // Say it creates an empty stack S\n" +
                    "\n" +
                    "    // Run while Q is not empty\n" +
                    "    while (!isEmpty(Q))\n" +
                    "    {\n" +
                    "        // deQueue an item from Q and push the dequeued item to S\n" +
                    "        push(&S, deQueue(Q));\n" +
                    "    }\n" +
                    " \n" +
                    "    // Run while Stack S is not empty\n" +
                    "    while (!isEmpty(&S))\n" +
                    "    {\n" +
                    "      // Pop an item from S and enqueue the poppped item to Q\n" +
                    "      enQueue(Q, pop(&S));\n" +
                    "    }\n" +
                    "}\n\n" +
                    "What does the above function do in general?\n\n",

            "Consider the following pseudo code. Assume that IntQueue is an integer queue. What does the function fun do?\n" +
                    "\n" +
                    "void fun(int n)\n" +
                    "{\n" +
                    "    IntQueue q = new IntQueue();\n" +
                    "    q.enqueue(0);\n" +
                    "    q.enqueue(1);\n" +
                    "    for (int i = 0; i < n; i++)\n" +
                    "    {\n" +
                    "        int a = q.dequeue();\n" +
                    "        int b = q.dequeue();\n" +
                    "        q.enqueue(b);\n" +
                    "        q.enqueue(a + b);\n" +
                    "        ptint(a);\n" +
                    "    }\n" +
                    "}\n" +
                    "\n",

            "An implementation of a queue Q, using two stacks S1 and S2, is given below:\n" +
                    "void insert(Q, x) {\n" +
                    "   push (S1, x);\n" +
                    "}\n" +
                    "  \n" +
                    "void delete(Q){\n" +
                    "   if(stack-empty(S2)) then \n" +
                    "      if(stack-empty(S1)) then {\n" +
                    "          print(“Q is empty”);\n" +
                    "          return;\n" +
                    "      }\n" +
                    "      else while (!(stack-empty(S1))){\n" +
                    "          x=pop(S1);\n" +
                    "          push(S2,x);\n" +
                    "      }\n" +
                    "   x=pop(S2);\n" +
                    "}\n" +
                    "\n" +
                    "Let n insert and m (<=n) delete operations be performed in an arbitrary order on an empty queue Q. Let x and y be the number of push and pop operations performed respectively in the process. Which one of the following is true for all m and n?\n" +
                    "\n",

            "Suppose you are given an implementation of a queue of integers. The operations that can be performed on the queue are:\n\n" +
                    "i. isEmpty (Q) — returns true if the queue is empty, false otherwise. \n" +
                    "ii. delete (Q) — deletes the element at the front of the queue and returns its value. \n" +
                    "iii. insert (Q, i) — inserts the integer i at the rear of the queue. \n" +
                    "\n" +
                    "Consider the following function:\n" +
                    "\n" +
                    " void f (queue Q) {\n" +
                    "int i ;\n" +
                    "\n" +
                    "if (!isEmpty(Q)) {\n" +
                    "   i = delete(Q);\n" +
                    "   f(Q);\n" +
                    "   insert(Q, i);\n" +
                    "  }\n" +
                    "}\n" +
                    "\n" +
                    "What operation is performed by the above function f ?\n\n",

            "A priority queue can efficiently implemented using which of the following data structures?\nAssume that the number of insert and peek (operation to see the current highest priority item) and extraction (remove the highest priority item) operations are almost same.\n"
    };

    private String mChoices[][] = {
            {"Removes the last from Q", "Keeps the Q same as it was before the call", "Makes Q empty", "Reverses the Q"},
            {"Prints numbers from 0 to n-1", "Prints numbers from n-1 to 0", "Prints first n Fibonacci numbers", "Prints first n Fibonacci numbers in reverse order"},
            {"n+m <= x < 2n and 2m <= y <= n+m", "n+m <= x < 2n and 2m<= y <= 2n", "2m <= x < 2n and 2m <= y <= n+m", "2m <= x <2n and 2m <= y <= 2n"},
            {"Leaves the queue Q unchanged", "Reverses the order of the elements in the queue Q", "Deletes the element at the front of the queue Q and inserts it at the rear keeping the other elements in the same order", "Empties the queue Q"},
            {"Array", "Linked List", "Heap Data Structures like Binary Heap, Fibonacci Heap", "None of the above"}
    };

    private int mCorrectAnswers[] = {3, 2, 0, 1, 2};

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