import java.util.Arrays;
//TODO add file, class and method header comments

public class ElizaTests {

	public static void testChooseString() {

		//is one of the 3 strings chosen?
		String [] strList = {"The", "happy", "cat"};
		String choice = Eliza.chooseString( strList);
		if ( choice != null && (choice.equals("The") || choice.equals("happy")
				|| choice.equals("cat"))) {
			System.out.println("testChooseString 1 passed.");
		} else {
			System.out.println("testChooseString 1 failed.");
		}

		//if I call it 100 times, are the choices approximately random?
		int countThe = 0;
		int countHappy = 0;
		int countCat = 0;
		for ( int i = 1; i <= 100; i++) {
			choice = Eliza.chooseString( strList);
			if ( choice != null) {
				if ( choice.equals("The")) {
					countThe++;
				} else if ( choice.equals("happy")) {
					countHappy++;
				} else if ( choice.equals("cat")) {
					countCat++;
				}
			}
		}
		if ( countThe >=20 && countThe <= 45
				&& countHappy >= 20 && countHappy <= 45
				&& countCat >= 20 && countCat <= 45) {
			System.out.println("testChooseString 2 passed. " + countThe
					+ "," + countHappy + "," + countCat);
		} else {
			System.out.println("testChooseString 2 failed. " + countThe
					+ "," + countHappy + "," + countCat);
		}

		//What should happen when an array with a single string is provided?
		String [] emptyString = {""};
		String ifEmptyString = Eliza.chooseString(emptyString);
		//String expectedEmptyString = null;
		if ( ifEmptyString.equals("")) {
			System.out.println("testChooseString 2 passed.");
		} else {
			System.out.println("testChooseString 2 failed. ");
		}

		//What should happen when null is passed to chooseString?
		String [] nullString = null;
		String ifNull = Eliza.chooseString(nullString);
		if ( ifNull ==  null) {
			System.out.println("testChooseString 3 passed.");
		} else {
			System.out.println("testChooseString 3 failed.");
		}
	}// testChooseString

	public static void testInList() {
		String [] quitWords = {"bye","goodbye","quit", "bye"};        
		// checking that a word that is expected in the list was found
		int index = Eliza.inList( "bye", quitWords);
		if ( index >= 0) {
			System.out.println("testInList 1 passed.");
		} else {
			System.out.println("testInList 1 failed.");
		}
		// checks the return value when a word is not found in the list.
		index = Eliza.inList( "seeya", quitWords);
		if ( index == -1) {
			System.out.println("testInList 2 passed.");
		} else {
			System.out.println("testInList 2 failed.");
		}

		//Make sure Eliza does not end if user inputs a part of a word
		// in the quit word list
		index = Eliza.inList ("good", quitWords);
		if ( index == -1) {
			System.out.println("testInList 3 passed.");
		} else {
			System.out.println("testInList 3 failed.");
		}


		//Test to see which index is returned if a word is listed more than once
		//in the list
		index = Eliza.inList ("bye", quitWords);
		if ( index == 0) {
			System.out.println("testInList 4 passed.");
		} else {
			System.out.println("testInList 4 failed.");
		}
	}//testInList

	public static void testAssemblePhrase() {
		// checks if words are appended
		String [] words = {"This", "is a", "sentence"};
		String sentence = Eliza.assemblePhrase( words);
		String expectedSentence = "This is a sentence";
		if ( sentence.equals( expectedSentence)) {
			System.out.println("testAssembleSentence 1 passed.");
		} else {
			System.out.println("testAssembleSentence 1 failed '" + sentence
					+ "'");
		}

		//test to see if null is returned when string is null
		String [] words1 = null;
		String ifNull = Eliza.assemblePhrase(words1);
		if ( ifNull ==  null) {
			System.out.println("testAssembleSentence 2 passed.");
		} else {
			System.out.println("testAssembleSentence 2 failed '" + sentence
					+ "'");
		}

		//test to see what happens to an array of an empty string
		String [] emptyString = {""};
		String ifEmptyString = Eliza.assemblePhrase(emptyString);
		String expectedEmptyString = "";
		if ( ifEmptyString.equals(expectedEmptyString)) {
			System.out.println("testAssembleSentence 3 passed.");
		} else {
			System.out.println("testAssembleSentence 3 failed '" + sentence
					+ "'");
		}
	} // testAssemblePhrase

	public static void testFindLongest() {
		String [] sentences = {"short", "This is longer.",
				"This is the longest one.", "s"};
		String longest = Eliza.findLongest( sentences);
		if ( longest == sentences[2]) {
			System.out.println("testFindLongest 1 passed.");
		} else {
			System.out.println("testFindLongest 1 failed.");
		}

		//test to see if the strings are same length then lowest index is
		//returned
		String [] sentencesOne = {"short", "This is longer.",
		"This is longer."};
		String longestOne = Eliza.findLongest( sentencesOne);
		if ( longestOne == sentencesOne[1]) {
			System.out.println("testFindLongest 2 passed.");
		} else {
			System.out.println("testFindLongest 2 failed.");
		}
	}// testFindLongest

	public static void testHowMany() {
		int countSpaces = Eliza.howMany( " ", " you me ");
		if ( countSpaces == 3) {
			System.out.println( "testHowMany 1 passed.");
		} else {
			System.out.println( "testHowMany 1 failed.  countSpaces == "
					+ countSpaces);
		}

		int countNum = Eliza.howMany("<2>", "What makes you think I am <2>?");
		if ( countNum == 1) {
			System.out.println( "testHowMany 2 passed.");
		} else {
			System.out.println( "testHowMany 2 failed.  countNum == "
					+ countNum);
		}

		// test to see if null substring case works
		int countNull = Eliza.howMany( null, " you me ");
		if ( countNull == -1) {
			System.out.println( "testHowMany 3 passed.");
		} else {
			System.out.println( "testHowMany 3 failed.  countNull == "
					+ countNull);
		}

		//test to see if null string case works
		int countNullString = Eliza.howMany( " ", null);
		if ( countNullString == -1) {
			System.out.println( "testHowMany 4 passed.");
		} else {
			System.out.println( "testHowMany 4 failed.  countNullString == "
					+ countNullString);
		}

		// test to see if string length is returned on an empty string
		int countEmptyString = Eliza.howMany( "", "Hello sir");
		if ( countEmptyString == 9) {
			System.out.println( "testHowMany 5 passed.");
		} else {
			System.out.println( "testHowMany 5 failed.  countEmptyString == "
					+ countEmptyString);
		}

		// test to see if sentences with same lengths work
		int countSameLength = Eliza.howMany( "apple", "Hello");
		if ( countSameLength == 0) {
			System.out.println( "testHowMany 6 passed.");
		} else {
			System.out.println( "testHowMany 6 failed.  countSameLength == "
					+ countSameLength);
		}

		// test to see if sentences with same word work
		int countSameWord = Eliza.howMany( "apple", "apple");
		if ( countSameWord == 1) {
			System.out.println( "testHowMany 7 passed.");
		} else {
			System.out.println( "testHowMany 7 failed.  countSameLength == "
					+ countSameWord);
		}

	}//howMany        

	public static void testFilterChars() {
		String userInput = "w? #t   i't e   4t m-s!";
		char [] expectedChars = {'w','!',' ',' ','t',' ',' ',' ','i','\'','t',
				' ','e',' ',' ',' ','4','t',' ','m',' ','s','!'};
		char [] characters = Eliza.filterChars( userInput);
		if ( userInput.length() == characters.length) {
			System.out.println("testFilterChars 1 passed.");
		} else {
			System.out.println("testFilterChars 1 failed.");
		}
		boolean error = false;
		for ( int i = 0; i < expectedChars.length; i++) {
			if ( expectedChars[i] != characters[i]) {
				System.out.print( String.format("characters[%d] '%c' expected "
						+ "+ '%c'\n", i, characters[i], expectedChars[i]));
				error = true;
			}
		}
		if ( error) {
			System.out.println("testFilterChars 2 failed.");
		} else {
			System.out.println("testFilterChars 2 passed.");
		}

		// test to see if null case works
		String userInput1 = null ;
		char [] characters1 = Eliza.filterChars( userInput1);
		if ( characters1 == null) {
			System.out.println("testFilterChars 3 passed.");
		} else {
			System.out.println("testFilterChars 3 failed.");
		}
	}// filter char

	public static void testRemoveDuplicateSpaces() {
		char [] chars1 = {'a', 't', ' ', '.', ' ', ' ', 'g', ' ', ' ', 'h', 
		' '};
		Eliza.removeDuplicateSpaces( chars1);
		char [] expectedResult = {'a', 't', ' ', '.', ' ', 'g', ' ', 'h', ' ',
				'\u0000', '\u0000'};

		boolean error = false;
		String errorStr = "";
		for ( int i = 0; i < expectedResult.length; i++) {
			if ( chars1[i] != expectedResult[i]) {
				errorStr += String.format("chars1[%d] '%c' expected '%c'\n", i, 
						chars1[i], expectedResult[i]);
				error = true;
			}
		}
		if ( error) {
			System.out.println("testRemoveDuplicateSpaces 1 failed. " 
					+ errorStr);
		} else {
			System.out.println("testRemoveDuplicateSpaces 1 passed.");
		}

	}//removeDuplicateSpaces

	public static void testFindAnyWords() {
		String[] someWords = {"Going", "now", "goodbye"};
		boolean found = Eliza.findAnyWords( someWords, Config.QUIT_WORDS);
		if ( found) {
			System.out.println("testFindAnyWords 1 passed.");
		} else {
			System.out.println("testFindAnyWords 1 failed.");
		}

		String[] someMoreWords = {"Hello", "how", "are", "you"};
		found = Eliza.findAnyWords( someMoreWords, Config.QUIT_WORDS);
		if ( !found) {
			System.out.println("testFindAnyWords 2 passed.");
		} else {
			System.out.println("testFindAnyWords 2 failed.");
		}

		//test to see if null case works
		String[] someWord = null;
		found = Eliza.findAnyWords( someWord, Config.QUIT_WORDS);
		if ( !found) {
			System.out.println("testFindAnyWords 3 passed.");
		} else {
			System.out.println("testFindAnyWords 3 failed.");
		}
	}//testFindAnyWords

	public static void testInitialProcessing() {
		String sentence = Eliza.initialProcessing("What? This isn't the "
				+ "    4th messy-sentence!");
		if ( sentence != null
				&& sentence.equals( "this isn't the 4th messy sentence")){
			System.out.println("testInitialProcessing 1 passed.");
		} else {
			System.out.println("testInitialProcessing 1 failed:" + sentence);
		}

		//test to see if null case works
		String []nullString = {null};
		String ifNull = Eliza.chooseString(nullString);
		if ( ifNull ==  null) {
			System.out.println("testInitialProcessing 2 passed.");
		} else {
			System.out.println("testInitialProcessing 2 failed.");
		}
	}

	public static void testReplacePairs() {
		String [] someWords = {"i'm", "cant", "recollect"};
		String [] beforeList = {"dont", "cant", "wont", "recollect", "i'm"};
		String [] afterList = {"don't", "can't", "won't", "remember", "i am"};
		String [] result = Eliza.replacePairs( someWords, beforeList, 
				afterList);
		if ( result != null && result[0].equals("i") && result[1].equals("am")
				&& result[2].equals("can't") && result[3].equals("remember")) {
			System.out.println("testReplacePairs 1 passed.");
		} else {
			System.out.println("testReplacePairs 1 failed.");
		}
	}//testReplacePairs


	public static void testFindPatternInSentence() {
		String [] pattern1 = { "computer"};
		String [] sentence1 = {"are", "you", "a", "computer"};

		String [] matches = Eliza.findPatternInSentence( pattern1, sentence1);
		if ( matches != null && matches.length == 2
				&& matches[0].equals( "are you a") && matches[1].equals("")) {
			System.out.println("testFindPatternInSentence 1 passed.");
		} else {
			System.out.println("testFindPatternInSentence 1 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern2 = { "computer"};
		String [] sentence2 = {"computer", "are", "you"};

		matches = Eliza.findPatternInSentence( pattern2, sentence2);
		if ( matches != null && matches.length == 2 && matches[0].equals("")
				&& matches[1].equals( "are you")) {
			System.out.println("testFindPatternInSentence 2 passed.");
		} else {
			System.out.println("testFindPatternInSentence 2 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern5 = { "computer"};
		String [] sentence5 = {"does", "that", "computer", "on", "your",
				"desk", "work"};
		matches = Eliza.findPatternInSentence( pattern5, sentence5);
		if ( matches != null && matches.length == 2
				&& matches[0].equals( "does that")
				&& matches[1].equals( "on your desk work")) {
			System.out.println("testFindPatternInSentence 3 passed.");
		} else {
			System.out.println("testFindPatternInSentence 3 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern6 = {"you", "me"};
		String [] sentence6 = {"why", "don't", "you", "like",  "me"};
		matches = Eliza.findPatternInSentence( pattern6, sentence6);
		if ( matches != null && matches.length == 3
				&& matches[0].equals( "why don't")
				&& matches[1].equals( "like") && matches[2].equals("")) {
			System.out.println("testFindPatternInSentence 4 passed.");
		} else {
			System.out.println("testFindPatternInSentence 4 failed.");
			System.out.println( Arrays.toString(matches));
		}

		String [] pattern7 = {"you", "me"};
		String [] sentence7 = {"me", "don't", "like", "you"};
		matches = Eliza.findPatternInSentence( pattern7, sentence7);
		if ( matches == null) {
			System.out.println("testFindPatternInSentence 5 passed.");
		} else {
			System.out.println("testFindPatternInSentence 5 failed.");
			System.out.println( Arrays.toString(matches));
		}

	}

	public static void testPrepareUserPhrase()  {
		String someWords = "i'm happy";
		String result = Eliza.prepareUserPhrase( someWords);
		if ( result != null && result.equals("you are happy")) {
			System.out.println("testPrepareUserPhrase 1 passed.");
		} else {
			System.out.println("testPrepareUserPhrase 1 failed. '" + result 
					+ "'");
		}

		//additional tests
	}

	public static void testPrepareResponse() {
		String draftResponse = "Really, <3>?";
		String []userPhrases = {"", "", "about my dog"};
		String response = Eliza.prepareResponse( draftResponse, userPhrases);

		String expectedResponse = "Really, about your dog?";

		if ( response.equals( expectedResponse)) {
			System.out.println("testPrepareResponse 1 passed.");
		} else {
			System.out.println("testPrepareResponse 1 failed. '" + response 
					+ "'");
		}
	}

	public static void testMatchResponse() {
		String []words1 = {"are", "you", "a", "computer"};
		String response1 = Eliza.matchResponse( words1);
		if ( Eliza.inList( response1, Config.RESPONSE_TABLE[0][1]) >= 0) {
			System.out.println("testMatchResponse 1 passed.");
		} else {
			System.out.println("testMatchResponse 1 failed.");
		}

		String []words2 = {"you", "are", "like", "my", "father"};
		String response2 = Eliza.matchResponse( words2);
		if ( response2 != null && response2.contains( "like your father")) {
			System.out.println("testMatchResponse 2 passed.");
		} else {
			System.out.println("testMatchResponse 2 failed. Output: " 
					+ response2);
		}

		//test to see if no match works
		String []words3 = {"school", "is", "fun"};
		String response3 = Eliza.matchResponse(words3);
		if (response3.contains("I'm not sure I understand you fully.")
				|| response3.contains("Please go on.") 
				|| response3.contains("I'm not sure I understand you fully.")
				|| response3.contains("What does that suggest to you?")
				|| response3.contains("Do you feel strongly about discussing "
						+ "such things?")){
			System.out.println("testMatchResponse 3 passed.");
		}// if
		else {
			System.out.println("testMatchResponse 2 failed. Output: " 
					+ response2);
		}// else
	}//matchResponseTest3

	private static void testProblem(String problem) {
		//supporting method for testProcessInput
		System.out.println("Patient:  " + problem);
		System.out.println("Eliza: " + Eliza.processInput( problem));
	}

	public static void testProcessInput() {
		//note: the responses may vary as they are randomly selected and the
		//random generator results will vary based on the previous times it
		//has been called. Therefore, see if each response is appropriate.

		//The following are selected phrases from:
		//http://web.stanford.edu/group/SHR/4-2/text/dialogues.html
		testProblem("Men are all alike.");
		testProblem("They're always bugging us about something specific "
				+ "or other.");
		testProblem("Well, my boyfriend made me come here.");
		testProblem("He says I'm depressed much of the time.");
		testProblem("It's true. I'm unhappy.");
		testProblem("I need some help, that much seems certain.");
		testProblem("Perhaps I could learn to get along with my mother.");
		testProblem("My mother takes care of me.");
		testProblem("My father.");
		testProblem("You are like my father in some ways.");
		testProblem("You are not very aggressive but I think you don't want me"
				+ " to notice that.");
		testProblem("You don't argue with me.");
		testProblem("You are afraid of me.");
		testProblem("My father is afraid of everybody.");
		testProblem("Bullies.");
	}

	public static void main(String[] args) {
		//feel free to comment out tests that are not of current interest
		//in order to focus on certain tests.  Eventually, all the tests
		//should run successfully.

		testChooseString();
		testInList();
		testAssemblePhrase();    
		testFindLongest();
		testHowMany();

		testFilterChars();
		testRemoveDuplicateSpaces();
		testFindAnyWords();
		testInitialProcessing();

		testReplacePairs();
		testFindPatternInSentence();
		testPrepareUserPhrase();
		testPrepareResponse();
		testMatchResponse();
		testProcessInput();

	}
}


