// @author Andy Cruz

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree tree=new MorseCodeTree();
	
	
	/**
	 * @return the data in the tree in LNR order separated by a space.
	 */
	
	public static String printTree() {
		String data="";
		ArrayList<String>list=new ArrayList<String>();
		list=tree.toArrayList();
		for(int i=0;i<list.size();i++) {
			data+=list.get(i);
		}
		return data;
	}
	
	/**
	 * @param morseCode the morse code
	 * @return english translation
	 */
	
	public static String convertToEnglish(String morseCode) {
		String[] codeWords;
		String[] codeLetters;
		String result="";
		codeWords=morseCode.split("/");
		for(int i=0;i<codeWords.length;i++) {
			codeWords[i]=codeWords[i].trim();
			codeLetters=codeWords[i].split(" ");
			for(int j=0;j<codeLetters.length;j++) {
				result+=tree.fetch(codeLetters[j]);
			}
			result+=" ";
		}
		result=result.trim();
		return result;
	}
	/**
	 * @param codeFile name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws FileNotFoundException 
	 */
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner in=new Scanner(codeFile);
		String result="";
		while(in.hasNext()) {
			result+=convertToEnglish(in.nextLine());
		}
		return result;
	}

}