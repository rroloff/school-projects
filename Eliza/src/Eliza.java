///////////////////////////////////////////////////////////////////////////////
//                   
// Title:            Eliza
// Files:            Config.java, ElizaTests.java, and Eliza.java
// Semester:         CS302 Spring 2016
//
// Author:           Rita Roloff
// Email:            rroloff@wisc.edu
// CS Login:         rita
// Lecturer's Name:  Jim Williams
// Lab Section:      312
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy)
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     Amrita Geddam
// Email:            geddam@wisc.edu
// CS Login:         geddam
// Lecturer's Name:  Jim Williams
// Lab Section:      311
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////

import java.util.Arrays;
import java.util.Scanner;

/**
 * (Write a succinct description of this class here. You should avoid
 * wordiness and redundancy. If necessary, additional paragraphs should
 * be preceded by &lt;p&gt;, the html tag for a new paragraph.)
 *
 * Bugs: None
 *
 * @author Rita Roloff
 */

public class Eliza {

	/**
	 * This method randomly picks one of the strings from the list. If list
	 * is null or has 0 elements then null is returned.
	 *
	 * @param list An array of strings to choose from.
	 * @return One of the strings from the list.
	 */        
	public static String chooseString( String[] list) {
		//list is null or length is zero, return null
		if ( list == null || list.length == 0){
			return null;
		}
		// index containing the location of the random string
		int index = Config.RNG.nextInt(list.length);
		//return the string of the random index
		return list[index]; 
	}

	/**
	 * Counts the number of times the substring is in the str. Does not
	 * count overlapping substrings separately. If either parameter
	 * substring or str is null then -1 is returned.
	 *
	 * Note: String methods indexOf may be useful for finding whether
	 *       substring occurs within str. One of the indexOf methods
	 *       has a parameter that says where to start looking in the str.
	 *       This might be useful to find the substring again in str,
	 *       once you have found a substring once.
	 *
	 * @param str The string to be searched.
	 * @param substring The string to be searched for within str.
	 * @return The number of times substring is found within str or -1 if
	 *         either parameter is null parameter.
	 */
	public static int howMany( String substring, String str) {
		//if string or substring is null, return -1
		if (str == null || substring == null){
			return -1;
		}// if brace
		//else if the substring is a space or enter,return its length
		else if (substring.equals("")){
			return str.length();
		}

		else {
			//counter for the substring
			int countOfSubstring = 0;
			//counter for the last location searched
			int lastLocationSearched = 0;

			/*if substring is found in a string increment
			 * the counter of substring
			 */
			while (lastLocationSearched <
					str.length() - substring.length() + 1){
				if (substring.equals( str.substring(lastLocationSearched,
						lastLocationSearched + substring.length()))){
					lastLocationSearched = lastLocationSearched
							+ substring.length();
					countOfSubstring++;
				}// if statement to count substring  

				/* increment last location searched if substring is not
				 * found in the string 
				 */
				else
					lastLocationSearched++;
			}// for loop to find substring
			return countOfSubstring;
		}// else brace
	}// how many brace

	/**
	 * If the word is found in the wordList then the index of the word
	 * is returned, otherwise -1 is returned. If there are multiple matches
	 * the index of the first matching word is returned. If either word or
	 * wordList is null then -1 is returned.
	 *
	 * @param word  A word to search for in the list.
	 * @param wordList  The list of Strings in which to search.
	 * @return The index of list where word is found, or -1 if not found.
	 */
	public static int inList( String word, String []wordList) {
		//if word equals null, return -1
		if (word == null){
			return -1;
		}

		/* increments through the for loop in order to see
		 * if a word is found in a list of words. If it is return the 
		 * index of the word in the word list
		 */
		// this loop runs through all the values of the word list. checkIndex 
		// is the index of the word list. If the word list element ever equals 
		//the word, it returns the index
		else{
			for (int checkIndex = 0; checkIndex < wordList.length; 
					checkIndex++){
				if (word.equals(wordList[checkIndex])){
					return checkIndex;
				}// else statement
			}// if word equals brace
		}// for loop- check index brace
		//returns -1 if word is not in the word list
		return -1; 
	}// in list

	/**
	 * Combines the Strings in list together with a space between each
	 * and returns the resulting string. If list is null then null is
	 * returned.
	 *
	 * @param list An array of words to be combined.
	 * @return A string containing all the words with a space between each.
	 */
	public static String assemblePhrase( String[] list) {
		//if list equals null, return null
		if (list == null){
			return null;
		}// brace if list is null

		//prints out an empty string before each word with a space
		String toReturn = "";
		/*increments through the list and combines string together with
		 * space in between
		 */

		for ( int i= 0; i < list.length; i++){
			toReturn += list[i];
			if(i != list.length-1){
				toReturn += " ";
			}// if
		}// brace for appending list
		return toReturn;
	}// brace for assemblePhrase method

	/**
	 * Returns the longest sentence, based on the String length, from
	 * the array of sentences. Removes spaces from the beginning and
	 * end of each sentence before comparing lengths. If sentences is null
	 * or has a length of 0 then null is returned.
	 *
	 * Note: String trim method may be useful.
	 *
	 * @param sentences The array of sentences passed in.
	 * @return The longest sentence without spaces at the beginning or end.
	 */
	public static String findLongest( String [] sentences) {
		if (sentences == null || sentences.length == 0){
			return null;
		}// brace if sentence is null or length 0

		// trims the first and following word in a sentence
		for (int i = 0; i < sentences.length; i++){
			sentences[i] = sentences[i].trim();
		} // trim for loop

		/* increments through sentence to find the longest word in the
		 * sentence. If it is the longest word, it will be stored in the 
		 * longestSentence variable.
		 */
		String longestSentence = "";
		int maxLength = 0;
		for ( int i = 0; i < sentences.length; i++){
			if (sentences[i].length() > maxLength) {
				maxLength = sentences[i].length();
				longestSentence = sentences[i];
			}

		}// sentence length for loop
		return longestSentence;    

	}// findLongest method

	/**
	 * This method performs the following processing to the userInput.
	 * - substitute spaces for all characters but (alphabetic characters,
	 *   numbers, and ' , ! ? .).
	 * - Change (,!?.) to (!). Parenthesis not included.
	 *
	 * @param userInput The characters that the user typed in.
	 * @return The character array containing the valid characters.
	 */    
	public static char [] filterChars( String userInput) {
		//if user input is null, return null
		if (userInput == null){
			return null; //replace with appropriate return value
		}// null case
		else {
			/* increments through the character array. If the array has a
			 * ',', '?', '.', then turn it into an '!'
			 */
			char[] character = userInput.toCharArray();
			// int i is the index of the character array
			for(int i= 0; i< character.length; i++){
				char characterChar = character[i];
				//if the element term is a comma, ? or ., they become !'s
				if (characterChar == ',' || characterChar == '?'
						|| characterChar == '.') {
					character[i] = '!';
				}

				/*if it is not any of the characters above, check if the 
				 * characters are either in the alphabet, digit, or !, in
				 * which case should not be changed
				 */
				else if (Character.isAlphabetic(character[i])
						||Character.isDigit(character[i])
						|| character[i] == '!'
						|| character[i] == '\''){
					character[i] = characterChar;
				}

				/* if it's none of the characters above then it turned into
				a space*/
				else{
					character[i] = ' ';
				}                
			}// for brace
			return character;
		}// else bracket
	}//filterChars

	/**
	 * Reduces all sequences of 2 or more spaces to 1 space within the
	 * characters array. If any spaces are removed then the same number
	 * of Null character '\u0000' will fill the elements at the end of the
	 * array.
	 *
	 * @param characters The series of characters that may have more than one
	 *     space in a row when calling. On return the array of characters will
	 *     not have more than one space in a row and there may be '\u0000'
	 *     characters at the end of the array.
	 */
	public static void removeDuplicateSpaces( char [] characters) {

		/* increments through the characters array. If the two continuous
		 * characters are spaces, then turn it into one space and add null
		 * at the end of the sentence array.
		 */
		for (int i = 1; i < characters.length; i++ ){
			if (characters[i] == ' ' && characters[i] == characters[i-1]){
				for (int j = i; j < characters.length - 1; j++){
					characters[j] = characters[j+1];        
				}// inner for loop
				characters[characters.length-1]= '\u0000';
				i--;
			}// if there are two spaces
		}// first for loop

	}// removeDuplicateSpaces

	/**
	 * Looks for each word in words within the wordList.
	 * If any of the words are found then true is returned, otherwise false.
	 *
	 * @param words List of words to look for.
	 * @param wordList List of words to look through.
	 * @return Whether one of the words was found in wordList.
	 */
	public static boolean findAnyWords(String[] words, String []wordList ){
		if (words == null){
			return false;
		}

		/* increments through the words list. If the index of a
		 *  word is found in the words list from the inList method then 
		 *  return true. Otherwise, return false.
		 */
		for (int i = 0; i < words.length; i++ ){
			int b = Eliza.inList(words[i], wordList);
			if (b >= 0){
				return true;
			}
		}
		return false;
	}//findAnyWords


	/**
	 * This method performs the following processing to the userInput and
	 * returns the longest resulting sentence.
	 * 1) Converts all characters to lower case  
	 *         See String toLowerCase() method.
	 * 2) Removes any extra spaces at the beginning or end of the input
	 *         See String trim() method.
	 * 3) Substitute spaces for all characters but alphabetic characters,
	 *    numbers, and ',.!? and then substitute ! for ,.?
	 *      See filterChars method.
	 * 4) Reduce all sequences of 2 or more spaces to be one space.
	 *      See removeDuplicateSpaces method.
	 * 5) Divide input into separate sentences based on !
	 *      Construct a String from a character array with
	 *          String str = new String( arrayName);
	 *      See String split method.
	 *      Example: String[] sentences = str.split("!");
	 * 6) Determine which sentence is longest in terms of characters and
	 *    return it.
	 *      See findLongest method.
	 *
	 * @param userInput The characters that the user typed in.
	 * @return The longest sentence from the input.
	 */
	public static String initialProcessing( String userInput) {
		if (userInput == null){
			return null; //replace with appropriate return value
		}
		//turn the userInput into all lowercase characters
		userInput = userInput.toLowerCase();
		// trim the userInput
		userInput = userInput.trim();
		// change unnecessary characters into valid one
		char [] newUserInput = Eliza.filterChars(userInput);
		Eliza.removeDuplicateSpaces(newUserInput); 
		String str = new String (newUserInput);
		String[] sentences = str.split("!");
		String s = Eliza.findLongest(sentences);
		return s;
	}// initialProcessing

	/**
	 * This method creates a new words list replacing any words it finds in
	 * the beforeList with words in the afterList. An array of the resulting
	 * words is returned.  
	 *
	 * @param words List of words to look through.
	 * @param beforeList List of words to look for.
	 * @param afterList List of words to replace, if the corresponding word in
	 * the before list is found in the words array.
	 * @return The new list of words with replacements.
	 */
	public static String[] replacePairs(String []words, String [] beforeList,
			String [] afterList) {

		/* Increments through the list of words until it find a word in the 
		 * before list. If inlist does not return -1 then switch the word
		 * found in before list with the word found in after list
		 */
		for (int i = 0; i < words.length; i++){
			int index = 0;
			if (inList (words[i], beforeList) != -1){
				index = inList (words[i], beforeList);
				words[i] = afterList[index];
			}// if
		}// for loop
		words = assemblePhrase(words).trim().split(" ");
		return words;
	}// replace pairs

	/**
	 * Checks to see if the pattern matches the sentence. In other words,
	 * checks to see that all the words in the pattern are in the sentence
	 * in the same order as the pattern. If the pattern matches then this
	 * method returns the phrases before, between and after the
	 * pattern words. If the pattern does not match then null is returned.
	 *
	 * @param pattern The words to match, in order, in the sentence.
	 * @param sentence Each word in the sentence.
	 * @return The phrases before, between and after the pattern words
	 *         or null if the pattern does not match the sentence.
	 */    
	public static String [] findPatternInSentence( String [] pattern,
			String [] sentence) {

		String [] userPhrase = new String [pattern.length + 1];
		for (int k = 0; k < userPhrase.length; k++){
			userPhrase[k] = "";
		}
		int phraseIndex = 0; //index of phrase
		int patternIndex = 0;// index of pattern
		for (int i = 0; i < sentence.length; i++){
			if (patternIndex < pattern.length && sentence[i].equals
					(pattern[patternIndex])){
				patternIndex++;
				phraseIndex++;
			}// if
			else{
				// adds space in front of array element if pattern in not found
				userPhrase[phraseIndex] += " " + sentence[i];
				userPhrase[phraseIndex] = userPhrase[phraseIndex].trim();
			}// else
		}// for loop
		if (patternIndex == pattern.length){
			return userPhrase;

		}// if
		else {
			return null;
		}// else if
	}// find pattern in sentence

	/**
	 * Replaces words in the phrase if they are in the
	 * Config.POST_PROCESS_BEFORE
	 * with the corresponding words from Config.POST_PROCESS_AFTER.
	 *
	 * @param phrase One or more words separated by spaces.
	 * @return A string composed of the words from phrase with replacements.
	 */
	public static String prepareUserPhrase( String phrase) {

		/* create a string array of all of the user phrase which are separated 
		 * by each space
		 */
		String [] newWordsList = phrase.split(" ");
		/* if a word is in the Config.POST_PROCESS_BEFORE, then turn it into
		 *  Config.POST_PROCESS_AFTER
		 */
		newWordsList = replacePairs(newWordsList,
				Config.POST_PROCESS_BEFORE,
				Config.POST_PROCESS_AFTER);
		String newAssembledPhrase = assemblePhrase(newWordsList);
		return newAssembledPhrase;    
	}

	/**
	 * Prepares a response based on the draftResponse. If draftResponse
	 * contains <1>, <2>, <3> or <4> then the corresponding userPhrase
	 * is substituted for the <1>, <2>, etc.  The userPhrase however must
	 * be prepared by exchanging words that are in Config.POST_PROCESS_BEFORE
	 * with the corresponding words from Config.POST_PROCESS_AFTER.
	 *
	 * @param draftResponse A response sentence potentially containing <1>,
	 *             <2> etc.
	 * @param userPhrases An array of phrases from the user input.
	 * @return A string composed of the words from sentence with replacements.
	 */
	public static String prepareResponse( String draftResponse,
			String []userPhrases) {

		// creates an empty string
		String switched = "";
		/* the final respones is initialized to the draft response which
		 * may contain invalid characters
		 * */
		String finalResponse = draftResponse;

		/* if it has contains <1>, <2>, <3> or <4> , then corresponding user
		 * phrase, which is prepared using the prepareUserPhrase method, is 
		 * the final response
		 */

		if (draftResponse.contains("<1>"))
		{
			switched = prepareUserPhrase(userPhrases[0]);
			finalResponse = draftResponse.replaceFirst("<1>",switched);
		}// if statement
		if (draftResponse.contains("<2>"))
		{
			switched = prepareUserPhrase(userPhrases[1]);
			finalResponse = draftResponse.replaceFirst("<2>",switched);
		}// if statement
		if (draftResponse.contains("<3>"))
		{
			switched = prepareUserPhrase(userPhrases[2]);
			finalResponse = draftResponse.replaceFirst("<3>",switched);
		}// if statement
		if (draftResponse.contains("<4>"))
		{
			switched = prepareUserPhrase(userPhrases[3]);
			finalResponse = draftResponse.replaceFirst("<4>",switched);
		}// if statement
		return finalResponse;
	}// prepare response
	/**
	 * Looks through Config.RESPONSE_TABLE to find the first pattern
	 * that matches the words. When a pattern is matched then a response
	 * is randomly chosen from the corresponding list of responses.
	 * If the response has a parameter denoted with <1>, <2>
	 * etc. the parameter is replaced with the 0th, 1st, etc String
	 * from the user phrases returned by the findPatternInSentence method.
	 *
	 * @param words The words of a sentence.
	 * @return The completed response ready to be shown to the user.
	 */
	public static String matchResponse( String [] words) {
		/*increments through the Config.Response_Table to see if a user's
		 * input matches the pattern. If it does, use the choose string method
		 * to choose a random string in the response set
		 */
		for (int i = 0; i < Config.RESPONSE_TABLE.length; i++){
			String [][] responseSet = Config.RESPONSE_TABLE[i];
			String []userPhrases  = findPatternInSentence( responseSet[0], 
					words);
			if (userPhrases != null) {
				String responseString =
						chooseString(Config.RESPONSE_TABLE
								[i][1]);
				String completedResponse = prepareResponse(responseString,
						userPhrases);
				return completedResponse;
			}// if
		}// for
		String noMatchString = chooseString(Config.NO_MATCH_RESPONSES);
		return noMatchString;
	}// matchResponse

	/**
	 * Takes the input as a parameter and returns a response. If any of the
	 * QUIT_WORDS are found then null is returned.
	 *
	 * @param userInput The problem sentence(s) the user types in.
	 * @return A response string to be shown to the user or null if a QUIT_WORD
	 *         is found.
	 */
	public static String processInput(String userInput) {
		if (userInput == null){
			return null;
		}
		/*processes the user input and creates a response based on the
		 * processed input
		 */
		else{
			String processedUserInput = initialProcessing(userInput);
			String [] listOfUserInput = processedUserInput.split(" ");

			if (findAnyWords(listOfUserInput, Config.QUIT_WORDS)){
				return null;
			}// if
			else{ 
				String [] replacedString = replacePairs(listOfUserInput, 
						Config.PRE_PROCESS_BEFORE, 
						Config.PRE_PROCESS_AFTER);
				String response = matchResponse(replacedString);
				return response;
			}// else
		}// outer else
	}// processInput

	/**
	 * This method displays an INITIAL_MESSAGE, accepts typed input, calls
	 * the processInput method, and prints out the response (of processInput)
	 * until the response is null at which point the FINAL_MESSAGE is shown
	 * and the program terminates.
	 */
	public static void interactive() {

		System.out.println("Eliza: " + Config.INITIAL_MESSAGE);
		Scanner input = new Scanner (System.in);
		System.out.print("Patient: ");

		/*While the reader is inputting a string, check to see if it is empty
		 * If it is, prompt the user for in input. Otherwise, continue to
		 * respond to the user until they enter a quit word. 
		 */
		while (input.hasNextLine()){
			String userInput = input.nextLine();
			if (!userInput.isEmpty()){
				String elizaResponse = processInput(userInput);
				if (elizaResponse == null){
					System.out.println("Eliza: " + Config.FINAL_MESSAGE);
				}
				else{
					System.out.println("Eliza: " + processInput(userInput));
					System.out.print("Patient: ");
				}// else
			}//if 
		}// while
		input.close();
	}// interactive 

	/**
	 * Program execution starts here.
	 * @param args unused
	 */      
	public static void main(String[] args) {

		interactive();

	}

}


