package com.research.ageac.gamified.quizlibrary.tree.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage2 extends MCQBaseClass {

    private String prerequisites = "1. Traversal in a Binary Tree\n2. Implementation of a Binary Tree";

    private String mQuestions[] = {
            "Consider the label sequences obtained by the following pairs of traversals on a labeled binary tree. Which of these pairs identify a tree uniquely ?\n" +
                    "\n" +
                    "\ti. preorder and postorder\n" +
                    "\tii. inorder and postorder\n" +
                    "\tiii. preorder and inorder\n" +
                    "\tiv. level order and postorder\n",

            "What does the following function do for a given binary tree?\n" +
                    "int fun(struct node *root)\n" +
                    "{\n" +
                    "    if (root == NULL)\n" +
                    "      return 0;\n" +
                    "    if (root->left == NULL && root->right == NULL)\n" +
                    "      return 0;\n" +
                    "    return 1 + fun(root->left) + fun(root->right);\n" +
                    "}\n",

            "Consider the pseudocode given below. The function DoSomething() takes as argument a pointer to the root of an arbitrary tree represented by the leftMostChild-rightSibling representation. Each node of the tree is of type treeNode.\n" +
                    "\n" +
                    "typedef struct treeNode* treeptr;\n" +
                    "struct treeNode\n" +
                    "{\n" +
                    "    treeptr leftMostChild, rightSibling;\n" +
                    "};\n" +
                    "int DoSomething (treeptr tree)\n" +
                    "{\n" +
                    "    int value=0;\n" +
                    "    if (tree != NULL)\n" +
                    "    {\n" +
                    "        if (tree->leftMostChild == NULL)\n" +
                    "            value = 1;\n" +
                    "        else\n" +
                    "            value = DoSomething(tree->leftMostChild);\n" +
                    "        value = value + DoSomething(tree->rightSibling);\n" +
                    "    }\n" +
                    "    return(value);\n" +
                    "}\n" +
                    "\n" +
                    "When the pointer to the root of a tree is passed as the argument to DoSomething, the value returned by the function corresponds to the\n",

            "Consider the following C program segment\n" +
                    "struct CellNode\n" +
                    "{\n" +
                    "    struct CelINode *leftchild;\n" +
                    "    int element;\n" +
                    "    struct CelINode *rightChild;\n" +
                    "}\n" +
                    " \n" +
                    "int DoSomething(struct CelINode *ptr)\n" +
                    "{\n" +
                    "    int value = 0;\n" +
                    "    if (ptr != NULL)\n" +
                    "    {\n" +
                    "      if (ptr->leftChild != NULL)\n" +
                    "          value = 1 + DoSomething(ptr->leftChild);\n" +
                    "      if (ptr->rightChild != NULL)\n" +
                    "          value = max(value, 1 + DoSomething(ptr->rightChild));\n" +
                    "    }\n" +
                    "    return (value);\n" +
                    "}\n\n" +
                    "The value returned by the function DoSomething when a pointer to the root of a non-empty tree is passed as argument is\n",

            "Following function is supposed to calculate the maximum depth or height of a Binary tree -- the number of nodes along the longest path from the root node down to the farthest leaf node.\n\n" +
                    "int maxDepth(struct node* node)\n" +
                    "{\n" +
                    "   if (node==NULL)\n" +
                    "     return 0;\n" +
                    "   else\n" +
                    "     {\n" +
                    "        /* compute the depth of each subtree */\n" +
                    "        int lDepth = maxDepth(node->left);\n" +
                    "        int rDepth = maxDepth(node->right);\n" +
                    "  \n" +
                    "        /* use the larger one */\n" +
                    "        if (lDepth > rDepth)\n" +
                    "            return X;\n" +
                    "        else return Y;\n" +
                    "     }\n" +
                    "}\n\n" +
                    "What should be the values of X and Y so that the function works correctly?\n"
    };

    private String mChoices[][] = {
            {"(i) only", "(ii), (iii)", "(iii) only", "(iv) only"},
            {"Counts leaf nodes", "Counts internal nodes", "Returns height where height is defined as number of edges on the path from root to deepest node", "Return diameter where diameter is number of edges on the longest path between any two nodes"},
            {"number of internal nodes in the tree", "height of the tree", "number of nodes without a right sibling in the tree", "number of leaf nodes in the tree"},
            {"The number of leaf nodes in the tree", "The number of nodes in the tree", "The number of internal nodes in the tree", "The height of the tree"},
            {"X = lDepth, Y = rDepth", "X = lDepth + 1, Y = rDepth + 1", "X = lDepth - 1, Y = rDepth -1", "None of the above"}
    };

    private int mCorrectAnswers[] = {1, 1, 3, 3, 1};

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