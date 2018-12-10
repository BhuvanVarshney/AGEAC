package com.research.ageac.gamified.quizlibrary.tree.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in Binary Tree";

    private String mQuestions[] = {
            "The inorder and preorder traversal of a binary tree are d b e a f c g and a b d e c f g, respectively. The postorder traversal of the binary tree is\n",
            "In a binary tree, the number of internal nodes of degree 1 is 5, and the number of internal nodes of degree 2 is 10. The number of leaf nodes in the binary tree is\n",
            "The following three are known to be the preorder, inorder and postorder sequences of a binary tree. But it is not known which is which.\n" +
                    "\n\tI. MBCAFHPYK\n" +
                    "\tII. KAMCBYPFH\n" +
                    "\tIII. MABCKYFPH\n\n" +
                    "Pick the true statement from the following.\n",
            "Which of the following cannot generate the full binary tree?\n",
            "The preorder and postorder traversal of a binary tree\na, b, d, e, c, f, g\nand\nd, e, b, f, g, c, a\nrespectively. The inorder traversal of the binary tree is\n"
    };

    private String mChoices[][] = {
            {"d e b f g c a", "e d b g f c a", "e d b f g c a", "d e f g b c a"},
            {"10", "11", "12", "15"},
            {"I and II are preorder and inorder sequences, respectively", "I and III are preorder and postorder sequences, respectively", "II is the inorder sequence, but nothing more can be said about the other two sequences", "II and III are the preorder and inorder sequences, respectively"},
            {"Inorder and Preorder", "Inorder and Postorder", "Preorder and Postorder", "None of the above"},
            {"d b e a f c g", "e d b g f c a", "No unique inorder", "Not possible to find an inorder"}
    };

    private int mCorrectAnswers[] = {0, 1, 3, 3, 2};

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