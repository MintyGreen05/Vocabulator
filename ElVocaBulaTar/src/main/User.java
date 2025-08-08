package main;
import java.util.*;
public class User {

	private int id;
	private String name;
	private ArrayList<SheetData> sheetsData = new ArrayList<SheetData>();
	private int masteryLevel = 0;
	public User(int id,String name) {
		super();
		this.id =id;
		this.name = name;
		
	}
    public void addSheetData(ArrayList<Sheet> mySheets){
		for(int i = 0; i<mySheets.size(); i++){
			sheetsData.add(new SheetData(this,name,mySheets.get(i)));
		}
	}
    public void saveSheetData(){
    	for(int i = 0; i<sheetsData.size(); i++){
			sheetsData.get(i).save(Pool.getSheets().get(i));
		}
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void inc_cEG(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).inc_cEG(wordIndex);
	}
	public void inc_mEG(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).inc_mEG(wordIndex);
	}
	public void inc_cGE(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).inc_cGE(wordIndex);
	}
	public void inc_mGE(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).inc_mGE(wordIndex);
	}
	
	public void print_EG(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).print_EG(wordIndex);
	}
	public void print_GE(int wordIndex, int sheetIndex){
		sheetsData.get(sheetIndex).print_GE(wordIndex);
	}
	
	public int getCorrect_EG(int wordIndex, int sheetIndex){
		return sheetsData.get(sheetIndex).getCorrect_EG(wordIndex);
	}
	public int getCorrect_GE(int wordIndex, int sheetIndex){
		return sheetsData.get(sheetIndex).getCorrect_GE(wordIndex);
	}
	public int getMistake_EG(int wordIndex, int sheetIndex){
		return sheetsData.get(sheetIndex).getMistake_EG(wordIndex);
	}
	public int getMistake_GE(int wordIndex, int sheetIndex){
		return sheetsData.get(sheetIndex).getMistake_GE(wordIndex);
	}
	
	public int getComp_EG(int sheetIndex){
		return sheetsData.get(sheetIndex).getComp_EG();
	}
	public int getComp_GE(int sheetIndex){
		return sheetsData.get(sheetIndex).getComp_GE();
	}
	public int getMasteryLevel() {
		return masteryLevel;
	}
	public void incMasteryLevel() {
		masteryLevel++;
	}
	
	
	
	
	
}
