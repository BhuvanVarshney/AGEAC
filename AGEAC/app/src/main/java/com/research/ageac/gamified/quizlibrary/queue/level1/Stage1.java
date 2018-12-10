package com.research.ageac.gamified.quizlibrary.queue.level1;

import com.research.ageac.gamified.quizlibrary.TrueFalseBaseClass;

public class Stage1 extends TrueFalseBaseClass {

    private String prerequisites = "1. Basics of Queue\n2. Implementation of Queue";

    private String mQuestions[] = {
            "Queue follows _____ Operation",
            "Queue is _____ data type",
            "_____ will be efficient to implement Queue",
            "In linked list implementation of queue:\n\nIn enqueue operation, if new nodes are inserted at the beginning of linked list, then in dequeue operation, nodes must be removed from the beginning\n",
            "In linked list implementation of queue:\n\nIn enqueue operation, if new nodes are inserted at the end, then in dequeue operation, nodes must be removed from the beginning"
    };

    private String mChoices[][] = {
            {"LIFO/FILO", "FIFO/LILO"},
            {"Linear", "Non Linear"},
            {"Array", "Linked List"},
            {"True", "False"},
            {"True", "False"}
    };

    private int mCorrectAnswers[] = {1, 0, 1, 1, 0};

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