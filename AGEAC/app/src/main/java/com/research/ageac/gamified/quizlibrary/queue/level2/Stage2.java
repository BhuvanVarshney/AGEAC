package com.research.ageac.gamified.quizlibrary.queue.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Queue";

    private String mQuestions[] = {
            "n elements of a Queue are to be reversed using another queue. The number of 'ENQUEUE' + 'DEQUEUE' operations required to do so is\n",
            "A queue has configuration a, b, c, d.\nTo get configuration d, c, b, a. One needs a minimum of\n",
            "A queue is implemented using an array such that ENQUEUE and DEQUEUE operations are performed efficiently. Which one of the following statements is CORRECT (n refers to the number of items in the queue)?\n",
            "Which data structure is used in Breadth First Search of a Graph to hold nodes?\n",
            "A data structure where elements can be added or removed at either end but not in the middle is called\n"
    };

    private String mChoices[][] = {
            {"n", "2n", "3n", "The task cannot be accomplished"},
            {"2 dequeue and 3 enqueue operations", "3 dequeue and 2 enqueue operations", "3 dequeue and 3 enqueue operations", "3 dequeue and 4 enqueue operations"},
            {"At most one operation can be performed in O(1) time but the worst case time for the other operation will be Ω(n)", "The worst case time complexity for both operations will be Ω(n)", "Worst case time complexity for both operations will be Ω(log n)", "None of the above"},
            {"Stack", "Queue", "Array", "Linked List"},
            {"Linked List", "Stack", "Queue", "DeQueue"}
    };

    private int mCorrectAnswers[] = {3, 2, 3, 1, 3};

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