package com.research.ageac.gamified.quizlibrary.hashing.level3;

import com.research.ageac.gamified.quizlibrary.MCQBaseClass;

public class Stage1 extends MCQBaseClass {

    private String prerequisites = "1. Implementation of Hashing Function(s)";

    private String mQuestions[] = {
            "If h is any hashing function and is used to hash ‘n’ distinct keys in to a table of size m, where n<m, the expected number of collisions involving a particular key x is\n",
            "A hash table of length 10 uses open addressing with hash function h(k)=k mod 10, and linear probing. After inserting 6 values into an empty hash table, the table looks like\n\n _,_,42,23,34,52,46,33,_,_\n [ ‘_’ denotes an empty location in the table]\n" +
                    "How many different insertion sequences of the key values using the same hash function and linear probing will result in the hash table shown above?\n",
            "Consider a hash table with 100 slots. Collisions are resolved using chaining. Assuming simple uniform hashing, what is the probability that the first 3 slots are unfilled after the first 3 insertions?\n",
            "Which one of the following hash functions on integers will distribute keys most uniformly over 10 buckets numbered 0 to 9 for i ranging from 0 to 2020?\n",
            "Consider a hash function that distributes keys uniformly. The hash table size is 20. After hashing of how many keys will the probability that any new key hashed collides with an existing one exceed 0.5\n"
    };

    private String mChoices[][] = {
            {"less than 1", "less than n", "less than m", "less than n/2"},
            {"10", "20", "30", "40"},
            {"(97 × 97 × 97)/100^3", "(99 × 98 × 97)/100^3", "(97 × 96 × 95)/100^3", "(97 × 96 × 95)/(3! × 100^3)"},
            {"h(i) = (i^2) mod 10", "h(i) = (i^3) mod 10", "h(i) = (11 ∗ i^2) mod 10", "h(i) = (12 ∗ i) mod 10"},
            {"5", "6", "7", "10"}
    };

    private int mCorrectAnswers[] = {0, 2, 0, 1, 3};

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