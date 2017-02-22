package com.cfsuman.me.kidoni;

import java.util.Random;

public class QuestionManager {
    private Random mRandom=new Random();

    public static int[] generateAdditionQuestion(int max, int min){
        Random mRandom = new Random();
        int num1 = mRandom.nextInt(max);
        int num2 = mRandom.nextInt(min);

        // Put the biggest value in num1
        int num3 = num1;
        if(num1 < num2){
            num1 = num2;
            num2 = num3;
        }

        int rightAnswer = num1+num2;

        // Random range minimum inclusive and maximum exclusive
        int random1 = mRandom.nextInt(5 - 1) + 1;
        int random2 = mRandom.nextInt(10 - 5) + 5;
        int random3 = mRandom.nextInt(5 - 1) + 1;

        int wrongAnswer1 = rightAnswer + random1;
        int wrongAnswer2 = rightAnswer + random2;
        int wrongAnswer3 = rightAnswer - random3;

        // Initialize a new Array of possible answers
        int[] answerArray = new int[]{wrongAnswer1,wrongAnswer2,wrongAnswer3,rightAnswer};
        answerArray = ShuffleArray(answerArray);
        int[] result = new int[7];
        result[0]=answerArray[0];
        result[1]=answerArray[1];
        result[2]=answerArray[2];
        result[3]=answerArray[3];
        result[4]=num1;
        result[5]=num2;
        result[6]=rightAnswer;

        // Shuffle the answers
        return result;

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
