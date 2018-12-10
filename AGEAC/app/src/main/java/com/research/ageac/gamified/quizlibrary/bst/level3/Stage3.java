package com.research.ageac.gamified.quizlibrary.bst.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage3 extends MCQBaseClass {

    private String prerequisites = "1. Basics of BST\n2. Implementation of BST\n3. Traversal in Binary Tree";

    private String mQuestions[] = {
            "How many distinct binary search trees can be created out of 4 distinct keys?\n",
            "In delete operation of BST, we need inorder successor (or predecessor) of a node when the node to be deleted has both left and right child as non-empty. Which of the following is true about inorder successor needed in delete operation?\n",
            "The number of ways in which the numbers\n\n1, 2, 3, 4, 5, 6, 7\n\ncan be inserted in an empty binary search tree, such that the resulting tree has height 6, is\n",
            "When searching for the key value 60 in a binary search tree, nodes containing the key values\n\n10, 20, 40, 50, 70 80, 90\n\nare traversed, not necessarily in the order given. How many different orders are possible in which these key values can occur on the search path from the root to the node containing the value 60?\n",
            "What does the function print() do in general? The function print() receives root of a Binary Search Tree (BST) and a positive integer k as arguments.\n" +
                    "\n" +
                    "// A BST node\n" +
                    "struct node {\n" +
                    "   int data;\n" +
                    "   struct node *left, *right;\n" +
                    "};\n" +
                    "\n" +
                    "int count = 0;\n" +
                    "\n" +
                    "void print(struct node *root, int k)\n" +
                    "{\n" +
                    "   if (root != NULL && count <= k)\n" +
                    "   {\n" +
                    "        print(root->right, k);\n" +
                    "        count++;\n" +
                    "        if (count == k)\n" +
                    "            printf(\"%d \", root->data);\n" +
                    "        print(root->left, k);\n" +
                    "   }\n" +
                    "}\n"
    };

    private String mChoices[][] = {
            {"5", "14", "24", "42"},
            {"Inorder Successor is always a leaf node", "Inorder successor may be an ancestor of the node", "Inorder successor is always either a leaf node or a node with empty left child", "Inorder successor is always either a leaf node or a node with empty right child"},
            {"2", "16", "32", "64"},
            {"35", "64", "128", "5040"},
            {"Prints the kth smallest element in BST", "Prints the kth largest element in BST", "Prints the leftmost node at level k from root", "Prints the rightmost node at level k from root"}
    };

    private int mCorrectAnswers[] = {1, 2, 3, 0, 1};

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