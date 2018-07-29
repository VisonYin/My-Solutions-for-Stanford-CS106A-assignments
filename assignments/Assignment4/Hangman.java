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
		//生成待猜测的单词
		String word = getRandomWord();
		//待猜测单词长度
		int lengthOfWord = word.length();
		//剩余可猜错次数，初始化为N_GUESSES
		int guessesLeft = N_GUESSES;
			
		//当前的猜测进度；假设word=HELLO,则outputWord形式为"H--LO",其中还未猜中的字母用"-"表示
		//初始化为"-----"
		String outputWord = "";
		for (int i = 0;i < lengthOfWord;i ++)
			outputWord += "-";
		
		//记录猜错的单词，储存在字符串中
		String INCORRECTGUESSES = "";
		//初始绘制
		resetCanvas(outputWord);
		
		println("Welcome to Hangman");
		println("Your word now looks like this: " + outputWord);
		
		//当有剩余次数时循环
		while(guessesLeft > 0) 	{
			println("You have " + guessesLeft + " guesses left.");
			//guess储存玩家猜测的字符
			String guess = readLine("Your guess: ");
			guess = guess.toUpperCase();
			//当玩家输入的非单个字母，给予错误提示，不扣除剩余次数。
			if ((guess.length() != 1) || ((guess.length() == 1)  && !Character.isLetter(guess.charAt(0))))	
				println("Please enter a single letter!");
			else	{
				//当玩家输入了之前已猜测过的字母，且该字母的确在word里时,给予错误提示，不扣除剩余次数
				//若该字母不在word里，即玩家重复猜测同意错误单词，这是按通常错误处理，扣除剩余次数
				if (outputWord.contains(guess))
					println("You have guessed this letter before,please enter another!");
				else	{
					//生成新的outputWord
					String OutputWord = newOutput(guess, word, outputWord);
					//猜错则减少剩余次数，移除一根线，并把单词记录在INCORRECTGUESSES中，并更新绘制
					if (OutputWord.equals(outputWord))	{
						guessesLeft --;
						INCORRECTGUESSES += guess;
						drawINCORRECTGUESSES(INCORRECTGUESSES);
						removeALine(N_GUESSES - guessesLeft -1);
						if (guessesLeft == 0)
							drawFlippedKarel(); 
					}
					//猜对则重新绘制labelPARTIALLYGUESSED
					else
						drawPARTIALLYGUESSED(OutputWord);
						
					outputWord = OutputWord;
				}
			}
			//玩家猜中整个word时，跳出循环，赢得游戏
			//否则，检查剩余次数；若无剩余次数，则失败；若有剩余次数继续游戏。
			if (isWin(outputWord, word))
				break;
			else
				if (guessesLeft == 0)
					println("You are completely hung.");
				else
					println("You word now looks like this: " + outputWord);
		}
		//游戏结束时，无论输赢，都输出正确的word		
		println("The word was: " + word);
	}
	
	//通过玩家猜测单词，返回新的outputWord
	private String newOutput(String guess, String word, String output)	{
		String newOutputWord = output;
		if (word.contains(guess))	{
			for (int i = 0;i < word.length();i ++)	{
				if (word.charAt(i) == guess.charAt(0))
					//当玩家猜测的单词在word中时，将outputWord对应位置的"-"换为该单词
					newOutputWord = replaceCharAt(i, guess, newOutputWord);
			}
			println("That guess is correct.");
		}
		else
			println("There are no " + guess + "'s in the word.");
		return newOutputWord;
	}
	
	//当玩家猜测的单词在word中时，将outputWord对应位置的"-"换为该单词
	private String replaceCharAt (int i, String guess, String output)	{
		String Output = "";
		Output = output.substring(0, i) + guess + output.substring(i+1);
		return Output;
	}
	
	//判断玩家是否赢得游戏
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
		//初始情况为有N_GUESSES条线
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
		//第i次猜错则移除第i条线
		canvas.remove(lines.get(i));
	}
	
	//绘制表示猜测情况的字符，形式为"H--LO"，位于canvas背景图底部
	private void drawPARTIALLYGUESSED(String partiallyGuessed)	{
		if (labelPARTIALLYGUESSED != null)
			canvas.remove(labelPARTIALLYGUESSED);
		labelPARTIALLYGUESSED = new GLabel(partiallyGuessed);
		labelPARTIALLYGUESSED.setFont(PARTIALLY_GUESSED_FONT);
		canvas.add(labelPARTIALLYGUESSED, canvas.getWidth()/2 - labelPARTIALLYGUESSED.getWidth()/2, PARTIALLY_GUESSED_Y);	
	}
	
	//记录并绘制猜测错误的单词，位于canvas背景图底部
	private void drawINCORRECTGUESSES(String incorrectGuesses)	{
		if (labelINCORRECTGUESSES != null)
			canvas.remove(labelINCORRECTGUESSES);
		labelINCORRECTGUESSES = new GLabel(incorrectGuesses);
		labelINCORRECTGUESSES.setFont(INCORRECT_GUESSES_FONT);
		canvas.add(labelINCORRECTGUESSES, canvas.getWidth()/2 - labelINCORRECTGUESSES.getWidth()/2, INCORRECT_GUESSES_Y);
	}
	
	//初始绘制
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
