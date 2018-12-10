package com.research.ageac.gamified.quizlibrary.bst.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Basics of BST\n2. Implementation of BST\n3. Traversal in Binary Tree";

    private String mQuestions[] = {
            "While inserting the elements\n71, 65, 84, 69, 67, 83\nin an empty binary search tree (BST) in the sequence shown, the element in the lowest level is\n",
            "Binary Search Tree (BST) stores values in the range 37 to 573. Consider the following sequence of keys.\n" +
                    "\n" +
                    "I. 81, 537, 102, 439, 285, 376, 305\n" +
                    "II. 52, 97, 121, 195, 242, 381, 472\n" +
                    "III. 142, 248, 520, 386, 345, 270, 307\n" +
                    "IV. 550, 149, 507, 395, 463, 402, 270\n" +
                    "\n" +
                    "Which of the following statements is TRUE?\n",

            "A Binary Search Tree (BST) stores values in the range 37 to 573. Consider the following sequence of keys.\n" +
                    "\n" +
                    "I. 81, 537, 102, 439, 285, 376, 305\n" +
                    "II. 52, 97, 121, 195, 242, 381, 472\n" +
                    "III. 142, 248, 520, 386, 345, 270, 307\n" +
                    "IV. 550, 149, 507, 395, 463, 402, 270\n" +
                    "\n" +
                    "Suppose the BST has been unsuccessfully searched for key 273. Which all of the above sequences list nodes in the order in which we could have encountered them in the search?\n",

            "Binary search tree is used to locate the number 43. \n\n" +
                    "(a) 61 52 14 17 40 43\n" +
                    "(b) 2 3 50 40 60 43\n" +
                    "(c) 10 65 31 48 37 43\n" +
                    "(d) 81 61 52 14 41 43\n" +
                    "(e) 17 77 27 66 18 43\n\n" +
                    "Then probe sequence\n",

            "The pre order traversal of a binary search tree is \n\n12,9,5,4,7,10,15,13,19,16\n\nThe post order traversal will be\n"
    };

    private String mChoices[][] = {
            {"65", "67", "69", "83"},
            {"I, II and IV are inorder sequences of three different BSTs", "I is a preorder sequence of some BST with 439 as the root", "II is an inorder sequence of some BST where 121 is the root and 52 is a leaf", "IV is a postorder sequence of some BST with 149 as the root"},
            {"II and III only", "I and III only", "III and IV only", "III only"},
            {"a and b are possible", "b and e are not possible", "a, c, d are not possible", "None of the above"},
            {"4, 7, 5, 10, 9, 13, 16, 19, 12, 15", "4, 5, 7, 10, 9, 16, 13, 19, 12, 15", "4, 7, 5, 10, 9, 13, 16, 19, 15, 12", "Need inorder traversal to find"}
    };

    private int mCorrectAnswers[] = {1, 2, 3, 1, 2};

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