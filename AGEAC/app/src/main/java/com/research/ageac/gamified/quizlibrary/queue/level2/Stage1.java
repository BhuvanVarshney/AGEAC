package com.research.ageac.gamified.quizlibrary.queue.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Queue\n2. Types of Queue";

    private String mQuestions[] = {
            "Suppose a circular queue of capacity (n – 1) elements is implemented with an array of n elements. Assume that the insertion and deletion operation are carried out using REAR and FRONT as array index variables, respectively.\nInitially, REAR = FRONT = 0.\nThe conditions to detect queue full and queue empty are\n",
            "A circularly linked list is used to represent a Queue. A single variable p is used to access the Queue. To which node should p point such that both the operations enQueue and deQueue can be performed in constant time?\n",
            "How many stacks are needed to implement a queue.\nConsider the situation where no other data structure like arrays, linked list is available to you\n",
            "Consider a situation where a client receives packets from a server. There may be differences in speed of the client and the server. Which data structure is best suited for synchronization?\n",
            "If queue(fixed size) is implemented using arrays(fixed size), what would be the worst run time complexity of queue and dequeue operations?\n"
    };

    private String mChoices[][] = {
            {"Full: (REAR+1) mod n == FRONT,\nempty: (FRONT+1) mod n == REAR", "Full: (REAR+1) mod n == FRONT,\nempty: REAR == FRONT", "Full: REAR == FRONT,\nempty: (REAR+1) mod n == FRONT", "Full: (FRONT+1) mod n == REAR,\nempty: REAR == FRONT"},
            {"Rear Node", "Front Node", "Node next to Front", "Not possible with a single pointer"},
            {"1", "2", "3", "4"},
            {"Linked List", "Priority Queue", "Queue", "Stack"},
            {"Ο(n), Ο(n)", "Ο(n), Ο(1)", "Ο(1), Ο(n)", "Ο(1), Ο(1)"}
    };

    private int mCorrectAnswers[] = {1, 0, 1, 2, 3};

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