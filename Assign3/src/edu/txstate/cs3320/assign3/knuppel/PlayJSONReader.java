package edu.txstate.cs3320.assign3.knuppel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlayJSONReader {
	

	public static String readPlay (String inputJSONFileName) {
		BufferedReader reader;

		StringBuilder buffer = new StringBuilder();
		String line;
		String thePlayAsJSONString = null;

		try {
			//	System.out.println ("Input file " + inputJSONFile);
			reader = new BufferedReader(new FileReader(inputJSONFileName));
			while ((line = reader.readLine()) != null) {
				// Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
				// But it does make debugging a *lot* easier if you print out the completed
				// buffer for debugging.
				buffer.append(line + "\n");
			}
			reader.close();
			thePlayAsJSONString = buffer.toString();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace();
			return null;
		}
		return thePlayAsJSONString;
	}
}