package edu.txstate.cs3320.assign3.knuppel;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AccessorUtils {
	
	private static final String ACTS = "acts";
	private static final String TITLE = "title";
	private static final String YEAR = "year";
	private static final String SCENE = "Scene";
	private static final String PARAGRAPHS = "paragraphs";
	private static final String CHARACTER = "charID";
	private static final String SPEECH_TEXT = "text";
	private static final String LOCATION = "location";
	
	/**
	 * Returns the names of the acts in the play as a list of strings in 
	 * ArrayList
	 * @param thePlay the entire play as a JSonObject
	 * @return names of play acts -- as ArrayList <String>
	 */
	public static ArrayList <String> getActsOfPlayByName (JsonObject thePlay) {
		JsonArray actsAsJsonObject =  thePlay.getAsJsonArray(ACTS);
		ArrayList <String> actNames = new ArrayList<String>();
		for (int i=0; i<actsAsJsonObject.size(); i++) {
			actNames.add(actsAsJsonObject.get(i).getAsString());
		}
		return actNames;
	}
	
	/**
	 * Returns the named act of the play as a JsonObject
	 * 
	 * @param thePlay the entire play as a JSonObject
	 * @param actName
	 * @return act as JsonObject
	 */
	
	public static JsonObject getActOfPlay (JsonObject thePlay, String actName) {
		return thePlay.get(actName).getAsJsonObject();
	}
	
	/**
	 * Returns the title of the play as a String
	 * @param thePlay  the entire play as a JSonObject
	 * @return play title as a String
	 */
	public static String getPlayTitle (JsonObject thePlay) {
		return thePlay.getAsJsonPrimitive(TITLE).getAsString();
	}
	
	/**
	 * Returns the year the play was written 
	 * @param thePlay  the entire play as a JSonObject
	 * @return the year as a String
	 */
	public static String getPlayYear (JsonObject thePlay) {
		return thePlay.getAsJsonPrimitive(YEAR).getAsString();
	}
	
	/**
	 * Returns an ArrayList of <i> names </i> of the scenes in a given act. 
	 * <b> Does not return the scene objects; just the names </b>
	 * The act (aPlayAct) is passed as a JsonObject. Note that aPlayAct 
	 * is a substructure in the JsonObject that is the play.
     * Scenes are names such as  "Scene1", "Scene2", etc. The
     * method checks that the scene is present in aPlayAct object 
     * and if it is, the scene's name is added to the result array.
     * 
	 * @param aPlayAct a JSonObject that is part of the plan (the enclosing
	 * JSonObject an ArrayList of the names of scenes in this Act
	 * @return  An ArrayList of the names of the scenes in this Act
	 */

	public static ArrayList <String>getScenesOfActByName(JsonObject aPlayAct) {
		int n = 1;
		String sceneName = SCENE + n;
		
		ArrayList<String> scenes = new ArrayList <String>();
		while (aPlayAct.has(sceneName)) {
			scenes.add(sceneName);
			n++;
			sceneName = SCENE + n;
		}
		return scenes;
	}
	
	/**
	 * Returns the JsonObject associated with the name  sceneName, which
	 * is either "Scene1", "Scene2" etc. This method differs from getScenesOfActByName
	 * in that it returns the JSonObject which is the scene
	 * Returns null if the scene is not present in aPlayAct
	 * 
	 * @param aPlayAct a JSonObject that is part of the plan (the enclosing
	 * JSonObject
	 * @param sceneName
	 * @return the scene -- a JsonObject
	 */
	public static JsonObject getSceneOfAct (JsonObject aPlayAct, String sceneName) {
		return aPlayAct.get(sceneName).getAsJsonObject();
	}
	
	/**
	 * Returns the location description for the scene
	 * @param scene
	 * @return the location of the scene -- a string
	 */
	public static String getSceneLocation (JsonObject scene) {
		return scene.get(LOCATION).getAsString();
	}
	
	
	/**
	 * Returns a JsonArray with all the paragraph JsonObjects in a scene.
	 * The scene is a JsonObject containing the array of paragraph objects.
	 * A scene is a substructure of an Act, the enclosing JSonOjbect. 
	 * Contents of each JsonArray element is the following: 
	 * 1. A character, 2.  their dialog, and a3.  paragraph number). 
	 * The scenes paragraphs are associated with the attribute PARAGRAPHS.
	 * 
	 * @param scene
	 * @return the paragraphs of a scene as a JsonArray
	 */

	public static JsonArray getSceneParagraphs (JsonObject scene ) {
		return scene.getAsJsonArray(PARAGRAPHS);
	}
	
	/**
	 * Returns the paragraph object (character, their dialog, and a paragraph number)
	 *  at index "pindex" from the array of scene paragraphs
	 * 
	 * @param paragraphs
	 * @param pIndex
	 * @return the specified paragraph of a scene as a JsonObject
	 */
	public static JsonObject getParagraphFromParagraphs (JsonArray paragraphs, int pIndex) {
		return  paragraphs.get(pIndex).getAsJsonObject();
	}
	
	/**
	 * Returns the name of the character from the paragraph object. This is the character
	 * that is speaking the dialog for this paragraph.
	 * 
	 * @param paragraph
	 * @return the name of the character speaking the paragraph dialog, as a String
	 */
	public static String getCharacterFromParagraph (JsonObject paragraph) {
		return paragraph.get(CHARACTER).getAsString() ;
	}
	
	/**
	 * Returns the dialog of the character from the paragraph.
	 * @param paragraph
	 * @return the dialog spoken by the character associated with this
	 * paragraph, as a String
	 */
	public static String getTextFromParagraph (JsonObject paragraph) {
		return paragraph.get(SPEECH_TEXT).getAsString() ;
	}
	
}
