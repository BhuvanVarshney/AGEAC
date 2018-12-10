package com.research.ageac.gamified.quizlibrary.hashing.level2;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Hashing Operations\n2. Hashing Functions";

    private String mQuestions[] = {
            "Consider a hash table of size seven, with starting index zero, and a hash function (3x + 4) mod 7. Assuming the hash table is initially empty, which of the following is the contents of the table when the sequence\n1, 3, 8, 10\nis inserted into the table using closed hashing? Note that ‘_’ denotes an empty location in the table\n",
            "A hash table of length 10 uses open addressing with hash function\nh(k)=k mod 10, and linear probing.\nAfter inserting 6 values into an empty hash table, the table looks like\n\n_,_,42,23,34,52,46,33,_,_\n[ ‘_’ denotes an empty location in the table]\n" +
                    "Which one of the following choices gives a possible order in which the key values could have been inserted in the table?\n",
            "Given the following input\n\n4322, 1334, 1471, 9679, 1989, 6171, 6173, 4199\n\nand the hash function (x mod 10)\nWhich of the following statements are true?\n" +
                    "i. 9679, 1989, 4199 hash to the same value \n" +
                    "ii. 1471, 6171 hash to the same value\n" +
                    "iii. All elements hash to the same value \n" +
                    "iv. Each element hashes to a different value\n",
            "Given a hash table T with 25 slots that stores 2000 elements, the load factor α for T is\n",
            "An advantage of chained hash table (external hashing) over the open addressing scheme is\n"
    };

    private String mChoices[][] = {
            {"8, _, _, _, _, _, 10", "1, 8, 10, _, _, _, 3", "1, _, _, _, _, _,3", "1, 10, 8, _, _, _, 3"},
            {"46, 42, 34, 52, 23, 33", "34, 42, 23, 52, 33, 46", "46, 34, 42, 23, 52, 33", "42, 46, 33, 23, 34, 52"},
            {"i only", "ii only", "iii or iv", "i and ii only"},
            {"80", "0.0125", "8000", "1.25"},
            {"Worst case complexity of search operations is less", "Space used is less", "Deletion is easier", "None of the above"}
    };

    private int mCorrectAnswers[] = {1, 2, 3, 0, 2};

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