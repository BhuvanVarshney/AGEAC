package com.research.ageac.gamified.quizlibrary.heap.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Basics of Heap";

    private String mQuestions[] = {
            "In a min heap\n",
            "In a max heap\n",
            "In the deletion operation of heap, the root is replaced by\n",
            "While searching for a value ‘k’(less than root node) in a max heap, if the node ‘m’ encountered in left subtree of root node has greater value than ‘k’ then\n",
            "Which of the following sequences of array elements forms a max-heap?\n"
    };

    private String mChoices[][] = {
            {"minimum values are stored", "child nodes have less value than parent nodes", "parent nodes have less value than child nodes", "maximum value is contained by the root node"},
            {"maximum values are stored", "child nodes have less value than parent nodes", "parent nodes have less value than child nodes", "minimum value is contained by the root node"},
            {"Next available value in the left sub-tree", "Next available value in the right sub-tree", "Any random value from the heap", "Last element of the last level"},
            {"The target node will be found below that subtree only", "The target node will be found in the left subtree of the node ‘m’", "The target node will be found in the right subtree of the node ‘m’", "None of the above"},
            {"{23, 17, 14, 6, 13, 10, 1, 12, 7, 5}", "{23, 17, 14, 6, 13, 10, 1, 5, 7, 12}", "{23, 17, 14, 7, 13, 10, 1, 5, 6, 12}", "{23, 17, 14, 7, 13, 10, 1, 12, 5, 7}"}
    };

    private int mCorrectAnswers[] = {2, 1, 3, 3, 2};

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