/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.io.*;
import java.util.*;
import java.util.ArrayList;


public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 5;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	/***********************************************************
	 *              Instance Variables                         *
	 ***********************************************************/
	
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	private ArrayList<GLine> lines = new ArrayList<GLine> ();
	GLabel labelPARTIALLYGUESSED;
	GLabel labelINCORRECTGUESSES;
	GImage karel;
	
	/***********************************************************
	 *                    Methods                              *
	 ***********************************************************/
	
	public void init() {
		add(canvas);
		}
	
	public void run() {
		//���ɴ��²�ĵ���
		String word = getRandomWord();
		//���²ⵥ�ʳ���
		int lengthOfWord = word.length();
		//ʣ��ɲ´��������ʼ��ΪN_GUESSES
		int guessesLeft = N_GUESSES;
			
		//��ǰ�Ĳ²���ȣ�����word=HELLO,��outputWord��ʽΪ"H--LO",���л�δ���е���ĸ��"-"��ʾ
		//��ʼ��Ϊ"-----"
		String outputWord = "";
		for (int i = 0;i < lengthOfWord;i ++)
			outputWord += "-";
		
		//��¼�´�ĵ��ʣ��������ַ�����
		String INCORRECTGUESSES = "";
		//��ʼ����
		resetCanvas(outputWord);
		
		println("Welcome to Hangman");
		println("Your word now looks like this: " + outputWord);
		
		//����ʣ�����ʱѭ��
		while(guessesLeft > 0) 	{
			println("You have " + guessesLeft + " guesses left.");
			//guess������Ҳ²���ַ�
			String guess = readLine("Your guess: ");
			guess = guess.toUpperCase();
			//���������ķǵ�����ĸ�����������ʾ�����۳�ʣ�������
			if ((guess.length() != 1) || ((guess.length() == 1)  && !Character.isLetter(guess.charAt(0))))	
				println("Please enter a single letter!");
			else	{
				//�����������֮ǰ�Ѳ²������ĸ���Ҹ���ĸ��ȷ��word��ʱ,���������ʾ�����۳�ʣ�����
				//������ĸ����word�������ظ��²�ͬ����󵥴ʣ����ǰ�ͨ���������۳�ʣ�����
				if (outputWord.contains(guess))
					println("You have guessed this letter before,please enter another!");
				else	{
					//�����µ�outputWord
					String OutputWord = newOutput(guess, word, outputWord);
					//�´������ʣ��������Ƴ�һ���ߣ����ѵ��ʼ�¼��INCORRECTGUESSES�У������»���
					if (OutputWord.equals(outputWord))	{
						guessesLeft --;
						INCORRECTGUESSES += guess;
						drawINCORRECTGUESSES(INCORRECTGUESSES);
						removeALine(N_GUESSES - guessesLeft -1);
						if (guessesLeft == 0)
							drawFlippedKarel(); 
					}
					//�¶������»���labelPARTIALLYGUESSED
					else
						drawPARTIALLYGUESSED(OutputWord);
						
					outputWord = OutputWord;
				}
			}
			//��Ҳ�������wordʱ������ѭ����Ӯ����Ϸ
			//���򣬼��ʣ�����������ʣ���������ʧ�ܣ�����ʣ�����������Ϸ��
			if (isWin(outputWord, word))
				break;
			else
				if (guessesLeft == 0)
					println("You are completely hung.");
				else
					println("You word now looks like this: " + outputWord);
		}
		//��Ϸ����ʱ��������Ӯ���������ȷ��word		
		println("The word was: " + word);
	}
	
	//ͨ����Ҳ²ⵥ�ʣ������µ�outputWord
	private String newOutput(String guess, String word, String output)	{
		String newOutputWord = output;
		if (word.contains(guess))	{
			for (int i = 0;i < word.length();i ++)	{
				if (word.charAt(i) == guess.charAt(0))
					//����Ҳ²�ĵ�����word��ʱ����outputWord��Ӧλ�õ�"-"��Ϊ�õ���
					newOutputWord = replaceCharAt(i, guess, newOutputWord);
			}
			println("That guess is correct.");
		}
		else
			println("There are no " + guess + "'s in the word.");
		return newOutputWord;
	}
	
	//����Ҳ²�ĵ�����word��ʱ����outputWord��Ӧλ�õ�"-"��Ϊ�õ���
	private String replaceCharAt (int i, String guess, String output)	{
		String Output = "";
		Output = output.substring(0, i) + guess + output.substring(i+1);
		return Output;
	}
	
	//�ж�����Ƿ�Ӯ����Ϸ
	private boolean isWin(String output, String word)	{
		if (output.equals(word))	{
			println("You win.");
		}
		return output.equals(word);
	}
	
	private void drawBackground() {
		GImage bg = new GImage("background.jpg");
		bg.setSize(canvas.getWidth(), canvas.getHeight());
		canvas.add(bg, 0, 0);
		}
	
	private void drawParachute()	{
		GImage parachute = new GImage("parachute.png");
		parachute.setSize(PARACHUTE_WIDTH, PARACHUTE_HEIGHT);
		canvas.add(parachute, (canvas.getWidth()- PARACHUTE_WIDTH)/2, PARACHUTE_Y);
	}
	
	private void drawKarel()	{
		karel = new GImage("karel.png");
		karel.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karel, (canvas.getWidth()- KAREL_SIZE)/2, KAREL_Y);
	}
	
	private void drawFlippedKarel()	{
		canvas.remove(karel);
		GImage karelFlipped = new GImage("karelFlipped.png");
		karelFlipped.setSize(KAREL_SIZE, KAREL_SIZE);
		canvas.add(karelFlipped, (canvas.getWidth()- KAREL_SIZE)/2, KAREL_Y);
	}
	
	private void drawLines()	{
		//��ʼ���Ϊ��N_GUESSES����
		int [] x = new int [N_GUESSES];
		int xBegin = canvas.getWidth()/2 - PARACHUTE_WIDTH/2;
		int d = PARACHUTE_WIDTH/(N_GUESSES - 1);
		int y = PARACHUTE_HEIGHT + PARACHUTE_Y;
		for (int i = 0; i < (N_GUESSES/2); i ++)	
			x[2*i + 1] = xBegin + i * d;
		for (int i = N_GUESSES - 1; i >= (N_GUESSES/2); i --)
			x[2*(N_GUESSES-i-1)] = xBegin + i * d;
		if (N_GUESSES % 2 != 0)
			x[N_GUESSES - 1] = xBegin + (N_GUESSES/2) * d;
		for (int i = 0; i < N_GUESSES; i ++)	{
			GLine line = new GLine(x[i], y, canvas.getWidth()/2, KAREL_Y);
			lines.add(line);
			canvas.add(lines.get(i));
		}
		
	}
	
	private void removeALine(int i)	{
		//��i�β´����Ƴ���i����
		canvas.remove(lines.get(i));
	}
	
	//���Ʊ�ʾ�²�������ַ�����ʽΪ"H--LO"��λ��canvas����ͼ�ײ�
	private void drawPARTIALLYGUESSED(String partiallyGuessed)	{
		if (labelPARTIALLYGUESSED != null)
			canvas.remove(labelPARTIALLYGUESSED);
		labelPARTIALLYGUESSED = new GLabel(partiallyGuessed);
		labelPARTIALLYGUESSED.setFont(PARTIALLY_GUESSED_FONT);
		canvas.add(labelPARTIALLYGUESSED, canvas.getWidth()/2 - labelPARTIALLYGUESSED.getWidth()/2, PARTIALLY_GUESSED_Y);	
	}
	
	//��¼�����Ʋ²����ĵ��ʣ�λ��canvas����ͼ�ײ�
	private void drawINCORRECTGUESSES(String incorrectGuesses)	{
		if (labelINCORRECTGUESSES != null)
			canvas.remove(labelINCORRECTGUESSES);
		labelINCORRECTGUESSES = new GLabel(incorrectGuesses);
		labelINCORRECTGUESSES.setFont(INCORRECT_GUESSES_FONT);
		canvas.add(labelINCORRECTGUESSES, canvas.getWidth()/2 - labelINCORRECTGUESSES.getWidth()/2, INCORRECT_GUESSES_Y);
	}
	
	//��ʼ����
	private void resetCanvas(String partiallyGuessed)	{
		canvas.removeAll();
		drawBackground();
		drawParachute();
		drawLines();
		drawKarel();
		drawPARTIALLYGUESSED(partiallyGuessed);
	}
	/**
	 * Method: Get Random Word
	 * -------------------------
	 * This method returns a word to use in the hangman game. It randomly 
	 * selects from among 10 choices.
	 */
	private String getRandomWord() {
		ArrayList<String> wordList = new ArrayList<String> ();
		try	{
			Scanner s = new Scanner(new File("HangmanLexicon.txt"));
			while (s.hasNextLine())	{
				String str = s.nextLine();
				wordList.add(str);
			}
			s.close();
		}	catch (IOException e)	{
			throw new RuntimeException(e);
		}
		
		int numsWord = wordList.size();
		int index = rg.nextInt(numsWord);
		return wordList.get(index);
	}

}
