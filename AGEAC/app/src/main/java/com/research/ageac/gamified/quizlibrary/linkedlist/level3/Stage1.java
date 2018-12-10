package com.research.ageac.gamified.quizlibrary.linkedlist.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {
    private String prerequisites = "1. Recursion\n2. Traversing a linked list\n3. Basics of set theory";

    private String mQuestions[] = {
            "What does the following function do for a given Linked List with first node as head?\n" +
                    "void fun1(struct node* head)\n" +
                    "{\n" +
                    "  if(head == NULL)\n" +
                    "    return;\n" +
                    "  fun1(head->next);\n" +
                    "  printf(\"%d  \", head->data);\n" +
                    "}\n",

            "What is the output of following function for start pointing to first node of following linked list?\n" +
                    "\n\t1->2->3->4->5->6\n\n" +
                    "void fun(struct node* start)\n" +
                    "{\n" +
                    "  if(start == NULL)\n" +
                    "    return;\n" +
                    "  printf(\"%d  \", start->data); \n" +
                    "  if(start->next != NULL )\n" +
                    "    fun(start->next->next);\n" +
                    "  printf(\"%d  \", start->data);\n" +
                    "}\n",

            "Given pointer to a node X in a singly linked list. Only one pointer is given, pointer to head node is not given, can we delete the node X from given linked list?\n",
            "Suppose there are two singly linked lists both of which intersect at some point and become a single linked list. The head or start pointers of both the lists are known, but the intersecting node and lengths of lists are not known. What is worst case time complexity of optimal algorithm to find intersecting node from two intersecting linked lists?\n\n(Given - m, n are lengths of given lists)\n",
            "Suppose each set is represented as a linked list with elements in arbitrary order. Which of the operations among union, intersection, membership, cardinality will be the slowest?\n"
    };

    private String mChoices[][] = {
            {"Prints all nodes of linked lists", "Prints all nodes of linked list in reverse order", "Prints alternate nodes of Linked List", "Prints alternate nodes in reverse order"},
            {"1 4 6 6 4 1", "1 3 5 1 3 5", "1 2 3 5", "1 3 5 5 3 1"},
            {"Possible if X is not last node. Use following two steps (a) Copy the data of next of X to X. (b) Delete  next of X", "Possible if size of linked list is even", "Possible if size of linked list is odd", "Possible if X is not first node. Use following two steps (a) Copy the data of next of X to X. (b) Delete next of X"},
            {"Θ(n*m)", "Θ(n^2), where m>n", "Θ(m+n)", "Θ(min(n, m))"},
            {"union only", "intersection, membership", "membership, cardinality", "union, intersection"}
    };

    private int mCorrectAnswers[] = {1, 3, 0, 2, 3};

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