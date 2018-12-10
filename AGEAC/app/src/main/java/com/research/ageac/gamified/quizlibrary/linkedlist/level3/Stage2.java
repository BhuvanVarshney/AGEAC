package com.research.ageac.gamified.quizlibrary.linkedlist.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {
    private String prerequisites = "1. Traversing a linked list\n2. Reversing a linked list";

    private String mQuestions[] = {
            "Consider the following function that takes reference to head of a Doubly Linked List as parameter. Assume that a node of doubly linked list has previous pointer as prev and next pointer as next.\n" +
                    "\nvoid fun(struct node **head_ref)\n" +
                    "{\n" +
                    "    struct node *temp = NULL;\n" +
                    "    struct node *current = *head_ref;\n" +
                    "    while (current !=  NULL)\n" +
                    "    {\n" +
                    "        temp = current->prev;\n" +
                    "        current->prev = current->next;\n" +
                    "        current->next = temp;\n" +
                    "        current = current->prev;\n" +
                    "    }\n" +
                    "    if(temp != NULL )\n" +
                    "        *head_ref = temp->prev;\n" +
                    "}\n" +
                    "\n" +
                    "Assume that reference of head of following doubly linked list is passed to above function\n\n\t1 <--> 2 <--> 3 <--> 4 <--> 5 <-->6\n\n What should be the modified linked list after the function call?\n",

            "The following C function takes a single-linked list of integers as a parameter and rearranges the elements of the list. The function is called with the list containing the integers 1, 2, 3, 4, 5, 6, 7 in the given order. What will be the contents of the list after the function completes execution?\n" +
                    "\n" +
                    "struct node \n" +
                    "{\n" +
                    "  int value;\n" +
                    "  struct node *next;\n" +
                    "};\n" +
                    "\n" +
                    "void rearrange(struct node *list)\n" +
                    "{\n" +
                    "  struct node *p, * q;\n" +
                    "  int temp;\n" +
                    "  if ((!list) || !list->next) \n" +
                    "      return;\n" +
                    "\n" +
                    "  p = list;\n" +
                    "  q = list->next;\n" +
                    "  while(q) \n" +
                    "  {\n" +
                    "     temp = p->value;\n" +
                    "     p->value = q->value;\n" +
                    "     q->value = temp;\n" +
                    "     p = q->next;\n" +
                    "     q = p?p->next:0;\n" +
                    "  }\n" +
                    "}\n",

            "The following function reverse() is supposed to reverse a singly linked list. There is one line missing at the end of the function.\n" +
                    "\n/* Link list node */\n" +
                    "struct node\n" +
                    "{\n" +
                    "    int data;\n" +
                    "    struct node* next;\n" +
                    "};\n" +
                    " \n" +
                    "/* head_ref is a double pointer which points to head (or start) pointer \n" +
                    "  of linked list */\n" +
                    "static void reverse(struct node** head_ref)\n" +
                    "{\n" +
                    "    struct node* prev   = NULL;\n" +
                    "    struct node* current = *head_ref;\n" +
                    "    struct node* next;\n" +
                    "    while (current != NULL)\n" +
                    "    {\n" +
                    "        next  = current->next;  \n" +
                    "        current->next = prev;   \n" +
                    "        prev = current;\n" +
                    "        current = next;\n" +
                    "    }\n" +
                    "    /*ADD A STATEMENT HERE*/\n" +
                    "}\n" +
                    "\n" +
                    "What should be added in place of \"/*ADD A STATEMENT HERE*/\", so that the function correctly reverses a linked list.\n",

            "The following C function takes a singly-linked list as input argument. It modifies the list by moving the last element to the front of the list and returns the modified list. Some part of the code is left blank. Choose the correct alternative to replace the blank line.\n" +
                    "\n" +
                    "typedef struct node \n" +
                    "{\n" +
                    "  int value;\n" +
                    "  struct node *next;\n" +
                    "}Node;\n" +
                    "\n" +
                    "Node *move_to_front(Node *head)\n" +
                    "{\n" +
                    "  Node *p, *q;\n" +
                    "  if ((head == NULL || (head->next == NULL)) \n" +
                    "    return head;\n" +
                    "\n" +
                    "  q = NULL; p = head;\n" +
                    "  while (p-> next !=NULL)\n" +
                    "  {\n" +
                    "    q = p;\n" +
                    "    p = p->next;\n" +
                    "  }\n" +
                    "  _______________________________\n" +
                    "  return head;\n" +
                    "}\n",

            "Consider the function f defined below.\n" +
                    "\n" +
                    "struct item \n" +
                    "{ \n" +
                    "  int data; \n" +
                    "  struct item * next; \n" +
                    "}; \n" +
                    "\n" +
                    "int f(struct item *p) \n" +
                    "{ \n" +
                    "  return (\n" +
                    "          (p == NULL) || \n" +
                    "          (p->next == NULL) || \n" +
                    "          (( P->data <= p->next->data) && f(p->next))\n" +
                    "         ); \n" +
                    "} \n" +
                    "\n" +
                    "For a given linked list p, the function f returns 1 if and only if\n"
    };

    private String mChoices[][] = {
            {"2 <--> 1 <--> 4 <--> 3 <--> 6 <-->5", "5 <--> 4 <--> 3 <--> 2 <--> 1 <-->6", "6 <--> 5 <--> 4 <--> 3 <--> 2 <--> 1", "6 <--> 5 <--> 4 <--> 3 <--> 1 <--> 2"},
            {"1, 2, 3, 4, 5, 6, 7", "2, 1, 4, 3, 6, 5, 7", "1, 3, 2, 5, 4, 7, 6", "2, 3, 4, 5, 6, 7, 1"},
            {"*head_ref = prev;", "*head_ref = current;", "*head_ref = next;", "*head_ref = NULL;"},
            {"q = NULL;\np->next = head;\nhead = p;", "q->next = NULL;\nhead = p;\np->next = head;", "head = p;\np->next = q;\nq->next = NULL;", "q->next = NULL;\np->next = head;\nhead = p;"},
            {"the list is empty or has exactly one element", "the elements in the list are sorted in non-decreasing order of data value", "the elements in the list are sorted in non-increasing order of data value", "not all elements in the list have the same data value"}
    };

    private int mCorrectAnswers[] = {2, 1, 0, 3, 1};

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