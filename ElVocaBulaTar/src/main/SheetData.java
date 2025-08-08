package main;
import java.util.*;
import main.ArrayListManager;
public class SheetData {

	private User myUser;
	private String userName;
	private Sheet mySheet;
	private Boolean mastered = false;
	private ArrayList<Boolean> doneEG = new ArrayList<>();
	private ArrayList<Boolean> doneGE = new ArrayList<>();
	private ArrayList<Integer> correctEG;
	private ArrayList<Integer> mistakeEG;
	private ArrayList<Integer> correctGE;
	private ArrayList<Integer> mistakeGE;
	
	private int numDoneEG = 0;
	private int numDoneGE = 0;
	
	public SheetData(User user,String userName,Sheet mySheet) {
		super();
		this.myUser = user;
		this.userName = userName;
		this.mySheet = mySheet;
		correctEG = new ArrayList<>(ArrayListManager.deserializeArrayList(userName,mySheet,"cEG"));
	    mistakeEG = new ArrayList<>(ArrayListManager.deserializeArrayList(userName,mySheet,"mEG"));
	    correctGE = new ArrayList<>(ArrayListManager.deserializeArrayList(userName,mySheet,"cGE"));
		mistakeGE = new ArrayList<>(ArrayListManager.deserializeArrayList(userName,mySheet,"mGE"));	
		
				
		for(int i = 0; i<correctEG.size();i++){
			if(correctEG.get(i)>=2&&correctEG.get(i)>=mistakeEG.get(i)){
				numDoneEG++;
				doneEG.add(true);
			}else{
				doneEG.add(false);
			}
			if(correctGE.get(i)>=3&&correctGE.get(i)>=mistakeGE.get(i)){
				numDoneGE++;
				doneGE.add(true);
			}else{
				doneGE.add(false);
			}
		}
		
		mastered();
		
		
	}
	public void mastered(){
        if(!mastered&&numDoneEG==numDoneGE&&numDoneEG==correctGE.size()){
			mastered = true;
			myUser.incMasteryLevel();
		}
	}
	public void save(Sheet mySheet){
		ArrayListManager.serializeArrayList(userName,mySheet,"cEG",correctEG);
		ArrayListManager.serializeArrayList(userName,mySheet,"mEG",mistakeEG);
		ArrayListManager.serializeArrayList(userName,mySheet,"cGE",correctGE);
		ArrayListManager.serializeArrayList(userName,mySheet,"mGE",mistakeGE);
	}
	
	public String getCompEG(){
		return (numDoneEG / correctEG.size()) + "%";
	}
	public String getCompGE(){
		return (numDoneGE / correctEG.size()) + "%";
	}
	public void inc_cEG(int wordIndex){
		correctEG.set(wordIndex,correctEG.get(wordIndex)+1);
		if(!doneEG.get(wordIndex)&&correctEG.get(wordIndex)>=2&&correctEG.get(wordIndex)>=mistakeEG.get(wordIndex)){
			doneEG.set(wordIndex,true);
			numDoneEG++;
			mastered();
		}
	}
	public void inc_mEG(int wordIndex){
		mistakeEG.set(wordIndex,mistakeEG.get(wordIndex)+1);
	}
	public void inc_cGE(int wordIndex){
		correctGE.set(wordIndex,correctGE.get(wordIndex)+1);
		if(!doneGE.get(wordIndex)&&correctGE.get(wordIndex)>=3&&correctGE.get(wordIndex)>=mistakeGE.get(wordIndex)){
			doneGE.set(wordIndex,true);
			numDoneGE++;
			mastered();
		}
		
	}
	
	public void inc_mGE(int wordIndex){
		mistakeGE.set(wordIndex,mistakeGE.get(wordIndex)+1);
	}
	
	public void print_EG(int wordIndex){
		System.out.print("rights: " + correctEG.get(wordIndex) +" wrongs: " + mistakeEG.get(wordIndex)) ;
	}
	public void print_GE(int wordIndex){
		System.out.print("rights: " + correctGE.get(wordIndex) +" wrongs: " + mistakeGE.get(wordIndex)) ;
	}
	
	public int getCorrect_EG(int wordIndex){
		return correctEG.get(wordIndex);
	}
	public int getCorrect_GE(int wordIndex){
		return correctGE.get(wordIndex);
	}
	public int getMistake_EG(int wordIndex){
		return mistakeEG.get(wordIndex);
	}
	public int getMistake_GE(int wordIndex){
		return mistakeGE.get(wordIndex);
	}
	
	public int getComp_EG(){
		 return (int)(((float)(numDoneEG))/(float)mySheet.getSize()*100);
	}
    public int getComp_GE(){
    	return (int)(((float)(numDoneGE))/(float)mySheet.getSize()*100);
	}
	
	
	
	
}
