package com.research.ageac.gamified.quizlibrary;

public class QuizCounts {
    public static int arrayLevel1Quizzes = 1;
    public static int arrayLevel2Quizzes = 5;
    public static int arrayLevel3Quizzes = 7;
    private static int arrayQuizzes = arrayLevel1Quizzes + arrayLevel2Quizzes + arrayLevel3Quizzes;

    public static int linkedListLevel1Quizzes = 1;
    public static int linkedListLevel2Quizzes = 3;
    public static int linkedListLevel3Quizzes = 2;
    private static int linkedListQuizzes = linkedListLevel1Quizzes + linkedListLevel2Quizzes + linkedListLevel3Quizzes;

    public static int stackLevel1Quizzes = 2;
    public static int stackLevel2Quizzes = 2;
    public static int stackLevel3Quizzes = 4;
    private static int stackQuizzes = stackLevel1Quizzes + stackLevel2Quizzes + stackLevel3Quizzes;

    public static int queueLevel1Quizzes = 1;
    public static int queueLevel2Quizzes = 2;
    public static int queueLevel3Quizzes = 1;
    private static int queueQuizzes = queueLevel1Quizzes + queueLevel2Quizzes + queueLevel3Quizzes;

    public static int treeLevel1Quizzes = 4;
    public static int treeLevel2Quizzes = 9;
    public static int treeLevel3Quizzes = 4;
    private static int treeQuizzes = treeLevel1Quizzes + treeLevel2Quizzes + treeLevel3Quizzes;

    public static int bstLevel1Quizzes = 1;
    public static int bstLevel2Quizzes = 2;
    public static int bstLevel3Quizzes = 3;
    private static int bstQuizzes = bstLevel1Quizzes + bstLevel2Quizzes + bstLevel3Quizzes;

    public static int heapLevel1Quizzes = 1;
    public static int heapLevel2Quizzes = 2;
    public static int heapLevel3Quizzes = 3;
    private static int heapQuizzes = heapLevel1Quizzes + heapLevel2Quizzes + heapLevel3Quizzes;

    public static int graphLevel1Quizzes = 1;
    public static int graphLevel2Quizzes = 3;
    public static int graphLevel3Quizzes = 8;
    private static int graphQuizzes = graphLevel1Quizzes + graphLevel2Quizzes + graphLevel3Quizzes;

    public static int hashingLevel1Quizzes = 1;
    public static int hashingLevel2Quizzes = 1;
    public static int hashingLevel3Quizzes = 1;
    private static int hashingQuizzes = hashingLevel1Quizzes + hashingLevel2Quizzes + hashingLevel3Quizzes;

    public static int totalQuizzes = arrayQuizzes + linkedListQuizzes + stackQuizzes + queueQuizzes + treeQuizzes + bstQuizzes + heapQuizzes + graphQuizzes + hashingQuizzes;
}
