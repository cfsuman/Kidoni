package com.cfsuman.me.kidoni;

import java.util.Random;

public class QuestionManager {
    private Random mRandom=new Random();

    public static Question generateAdditionQuestion(int max, int min){
        Question question = new Question();
        Random random = new Random();

        int num1 = random.nextInt(max);
        int num2 = random.nextInt(min);

        // Put the biggest value in num1
        int num3 = num1;
        if(num1 < num2){
            num1 = num2;
            num2 = num3;
        }
        question.setNum1(num1);
        question.setNum2(num2);

        int rightAnswer = num1+num2;
        question.setResult(rightAnswer);

        // Random range minimum inclusive and maximum exclusive
        int random1 = random.nextInt(5 - 1) + 1;
        int random2 = random.nextInt(10 - 5) + 5;
        int random3 = random.nextInt(5 - 1) + 1;

        int wrongAnswer1 = rightAnswer + random1;
        int wrongAnswer2 = rightAnswer + random2;
        int wrongAnswer3 = rightAnswer - random3;

        // Initialize a new array of possible answers
        int[] answerArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};
        answerArray = ShuffleArray(answerArray);

        question.setA(answerArray[0]);
        question.setB(answerArray[1]);
        question.setC(answerArray[2]);
        question.setD(answerArray[3]);

        // Return the question
        return question;
    }

    public static Question generateSubtractionQuestion(int max, int min) {
        Question question = new Question();
        Random random = new Random();

        int num1 = random.nextInt(max);
        int num2 = random.nextInt(min);

        // Put the biggest value in num1
        int num3 = num1;
        if(num1 < num2){
            num1 = num2;
            num2 = num3;
        }
        question.setNum1(num1);
        question.setNum2(num2);


        int rightAnswer = num1-num2;
        question.setResult(rightAnswer);

        // Random range minimum inclusive and maximum exclusive
        int random1 = random.nextInt(5 - 1) + 1;
        int random2 = random.nextInt(10 - 5) + 5;
        int random3 = random.nextInt(5 - 1) + 1;

        int wrongAnswer1 = rightAnswer + random1;
        int wrongAnswer2 = rightAnswer + random2;
        int wrongAnswer3 = rightAnswer - random3;

        // Initialize a new array of possible answers
        int[] answerArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};
        answerArray = ShuffleArray(answerArray);

        question.setA(answerArray[0]);
        question.setB(answerArray[1]);
        question.setC(answerArray[2]);
        question.setD(answerArray[3]);

        // Return the question
        return question;
    }
    public static Question generateMultiplicationQuestion(int max, int min) {
        Question question = new Question();
        Random random = new Random();

        int num1 = random.nextInt(max);
        int num2 = random.nextInt(min);

        // Put the biggest value in num1
        int num3 = num1;
        if(num1 < num2){
            num1 = num2;
            num2 = num3;
        }
        question.setNum1(num1);
        question.setNum2(num2);


        int rightAnswer = num1*num2;
        question.setResult(rightAnswer);

        // Random range minimum inclusive and maximum exclusive
        int random1 = random.nextInt(5 - 1) + 1;
        int random2 = random.nextInt(10 - 5) + 5;
        int random3 = random.nextInt(5 - 1) + 1;

        int wrongAnswer1 = rightAnswer + random1;
        int wrongAnswer2 = rightAnswer + random2;
        int wrongAnswer3 = rightAnswer - random3;

        // Initialize a new array of possible answers
        int[] answerArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};
        answerArray = ShuffleArray(answerArray);

        question.setA(answerArray[0]);
        question.setB(answerArray[1]);
        question.setC(answerArray[2]);
        question.setD(answerArray[3]);

        // Return the question
        return question;
    }
    public static Question generateDivisionQuestion(int max, int min) {
        Question question = new Question();
        Random random = new Random();

        int num1 = random.nextInt(max - 1) + 1;
        int num2 = random.nextInt(min - 1) + 1;

        // Put the biggest value in num1
        int num3 = num1;
        if(num1 < num2){
            num1 = num2;
            num2 = num3;
        }


        int multiply = num1*num2;
        int rightAnswer = multiply/num2;

        question.setNum1(multiply);
        question.setNum2(num2);
        question.setResult(rightAnswer);

        // Random range minimum inclusive and maximum exclusive
        int random1 = random.nextInt(5 - 1) + 1;
        int random2 = random.nextInt(10 - 5) + 5;
        int random3 = random.nextInt(5 - 1) + 1;

        int wrongAnswer1 = rightAnswer + random1;
        int wrongAnswer2 = rightAnswer + random2;
        int wrongAnswer3 = rightAnswer - random3;

        // Initialize a new array of possible answers
        int[] answerArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};
        answerArray = ShuffleArray(answerArray);

        question.setA(answerArray[0]);
        question.setB(answerArray[1]);
        question.setC(answerArray[2]);
        question.setD(answerArray[3]);

        // Return the question
        return question;
    }


        public static int[] ShuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

}
