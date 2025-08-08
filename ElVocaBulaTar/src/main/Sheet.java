package main;
import java.util.*;
public class Sheet {

	private boolean active;
	private int sheetNum;
	private String name;
	
	private int size;
	private ArrayList<Word> words = new ArrayList<Word>();
	public Sheet(int sheetNum, String name, ArrayList<Word> words2, int size) {
		super();
		active = true;
		this.sheetNum = sheetNum;
		this.name = name;
		for(int i =0; i<words2.size();i++){
			this.words.add(words2.get(i));
		}
		this.size = size;
	}
	public int getSheetNum() {
		return sheetNum;
	}
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Word> getWords() {
		return words;
	}
	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void makeInactive(){
		active = false;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
