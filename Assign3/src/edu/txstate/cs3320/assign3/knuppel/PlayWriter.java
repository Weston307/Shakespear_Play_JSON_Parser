package edu.txstate.cs3320.assign3.knuppel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 * PlayWriter class takes a Json file and writes
 * it to a text file.
 * @author Weston
 * 
 */
public class PlayWriter {
	private static String inputThePlay = "./iofiles/MidsummerNightsDream.json";
	private static String outputFile = "./iofiles/outputFile.txt";
	
	//Holds the string version of the Json file.
	private static String thePlayAsJSONString = PlayJSONReader.
										readPlay(inputThePlay);
	//Holds the act names: Act1 - Act5.
	public static ArrayList <String> actByNames;
	//Holds the scene names in each Act.
	public static ArrayList <String> sceneNames;
	private static BufferedWriter writer;

	public static JsonElement jelement = new JsonParser().
			parse(thePlayAsJSONString);
	public static JsonObject thePlayJsonObject = (JsonObject)jelement;
	
	//This allows for the printing of the correct Act name in front of 
	//each scene name. Found in writeScenesOfAct
	private static int increment = 0;

	/**
	 * main calls writePlay()
	 * @param args
	 */
	public static void main(String[] args) 
	{
		writePlay();
	}
	
	/**
	 * writePlay() calls writePlayTitleAndYear()
	 */
	private static void writePlay()
	{
		writePlayTitleAndYear();
	}
	
	/**
	 * writePlayTitleAndYear() creates the writer,
	 * brings in the title and year of the play,
	 * writes both the title and the year to 
	 * outputFile.txt, and finally calls writeActs().
	 */
	private static void writePlayTitleAndYear()
	{
		try {
			writer = new BufferedWriter(new FileWriter(outputFile));
			writer.write(AccessorUtils.getPlayTitle (thePlayJsonObject));
			writer.newLine();
			writer.write(AccessorUtils.getPlayYear (thePlayJsonObject));
			writer.newLine();
			writer.flush();
			writeActs();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * writeActs() brings in all the acts by name
	 * and prints them all to outputFile.txt. It then
	 * calls writeAct(String) and sends it the an act
	 * name.
	 */
	private static void writeActs()
	{
		actByNames = new ArrayList<String>(AccessorUtils.
				getActsOfPlayByName (thePlayJsonObject));
		for(int i = 0; i < actByNames.size(); i++)
		{
			try {
				writer.write(actByNames.get(i));
				writer.newLine();
				writer.flush();
				writeAct(actByNames.get(i));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * writeAct(String) brings in the object of the
	 * specified act that was sent by writeActs().
	 * It then sends this object to 
	 * writeScenesOfAct(JsonObject);
	 * @param actName
	 */
	private static void writeAct(String actName)
	{

			JsonObject jsonObject = AccessorUtils.getActOfPlay
					(thePlayJsonObject, actName);
			writeScenesOfAct(jsonObject);
	}
	
	/**
	 * writeScenesOfAct(JsonObject) takes an act
	 * JsonObject and pulls the scene names from that
	 * act and stores them in the String array scenNames.
	 * sceneNames is written to outputFile.txt and then
	 * it calls writeSceneOfAct(JsonObject, String) and
	 * sends it an act and scene name.
	 * @param anAct
	 */
	private static void writeScenesOfAct(JsonObject anAct)
	{
		sceneNames = new ArrayList<String>(AccessorUtils.
				getScenesOfActByName(anAct));
		try {
			for(int i = 0; i < sceneNames.size(); i++)
			{
				//This will help print the act in front of each scene.
				writer.write(actByNames.get(increment) +"," + " ");
				if(i == 1)
					increment++;
				writer.write(sceneNames.get(i));
				writer.newLine();
				writer.flush();
				writeSceneOfAct(anAct, sceneNames.get(i));
			}
		}catch(IOException e) {
				e.printStackTrace();
		}
	}
	
	/**
	 * writeSceneOfAct(JsonObject, String) takes an act
	 * and a scene name and brings in that scene object.
	 * It then brings in the scene location which is not
	 * used in this program. Finally it calls
	 * writeSceneParagraphs(JsonObject) and sends it a
	 * scene object.
	 * @param anAct
	 * @param sceneName
	 */
	private static void writeSceneOfAct(JsonObject anAct, String sceneName)
	{
		JsonObject anActsScene = AccessorUtils.getActOfPlay
							(anAct, sceneName);
		//Did not see a need for this in this assignment.
		//String location = AccessorUtils.getSceneLocation (anActsScene);
		writeSceneParagraphs(anActsScene);
	}
	
	/**
	 * writeSceneParagraphs(JsonObject) takes a secne object
	 * and pulls the paragraphs in that scene and stores them
	 * in a JsonArray called array. It then calls
	 * writeParagraphParagraphs(JsonArray, int) and sends it 
	 * the JsonArray of paragraphs and an index of the array
	 * @param scene
	 */
	private static void writeSceneParagraphs(JsonObject scene)
	{
		JsonArray array = AccessorUtils.getSceneParagraphs (scene); 
		for (int i = 0; i < array.size(); i++) 
			writeParagraphOfParagraphs(array, i);
	}
	
	/**
	 * writeParagraphParagraphs(JsonArray, int) takes a
	 * JsonArray called paragraph and an index called
	 * index and gets the paragraph within the JsonArray
	 * by its index. It pulls the character and the
	 * text from the paragraph and writes those to
	 * outputFile.txt.
	 * @param paragraph
	 * @param index
	 */
	private static void writeParagraphOfParagraphs(JsonArray paragraph, int index)
	{
		JsonObject paragraphs = AccessorUtils.getParagraphFromParagraphs
														(paragraph, index);
		String charID = AccessorUtils.getCharacterFromParagraph(paragraphs);
		String text = AccessorUtils.getTextFromParagraph(paragraphs);
		try {
			writer.write(charID);
			writer.newLine();
			writer.write(text);
			writer.newLine();
			writer.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}