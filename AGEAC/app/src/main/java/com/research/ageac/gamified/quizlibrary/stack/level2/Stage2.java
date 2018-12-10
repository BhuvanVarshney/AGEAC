package com.research.ageac.gamified.quizlibrary.stack.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Stack";

    private String mQuestions[] = {
            "Which data structure can be used in non-recursive implementation of a recursive algorithm\n",
            "Suppose a stack is to be implemented with a linked list instead of an array. What would be the effect on the time complexity of the push and pop operations of the stack implemented using linked list (Assuming stack is implemented efficiently)?\n",
            "Which of the following is true about linked list implementation of stack?\n",
            "UNDO-REDO operation will require ___ stack(s)\n",
            "Stack is used for\n"
    };

    private String mChoices[][] = {
            {"Stack", "Linked list", "Trees", "Queue"},
            {"O(1) for insertion and O(n) for deletion", "O(1) for insertion and O(1) for deletion", "O(n) for insertion and O(1) for deletion", "O(n) for insertion and O(n) for deletion"},
            {"In push operation, if new nodes are inserted at the beginning of linked list, then in pop operation, nodes must be removed from end", "In push operation, if new nodes are inserted at the end, then in pop operation, nodes must be removed from the beginning", "In push operation, if new nodes are inserted at the beginning, then in pop operation, nodes must also be removed from the beginning", "None of the above"},
            {"1", "2", "4", "Can’t be done using stack"},
            {"CPU Resource Allocation", "Breadth First Traversal", "Recursion", "All of the above"}
    };

    private int mCorrectAnswers[] = {0, 1, 2, 1, 2};

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