package main;
import java.util.*;
import java.io.*;

public class ArrayListManager {

	public static void serializeArrayList(String userName ,Sheet mySheet, String mode ,ArrayList<Integer> arr){
		try (FileOutputStream fos = new FileOutputStream(userName+mySheet.getName()+mode);
			    ObjectOutputStream oos = new ObjectOutputStream(fos);) {

			  oos.writeObject(arr);

			}catch (IOException ioe) {
			  
			  ioe.printStackTrace();
			}
	}
    public static ArrayList<Integer> deserializeArrayList(String userName ,Sheet mySheet, String mode){
    	ArrayList<Integer> list = null;

    	try (FileInputStream fis = new FileInputStream(userName+mySheet.getName()+mode);
    		    ObjectInputStream ois = new ObjectInputStream(fis);) {

    		  list = (ArrayList<Integer>) ois.readObject();
    		} catch (IOException ioe) {
    			System.out.println("Updated");
      		  ArrayList<Integer> list2 = new ArrayList<Integer>();
      		  for(int i = 0; i<mySheet.getWords().size();i++){  
      			  list2.add(0);			
      			}
      		  return list2;
    		} catch (ClassNotFoundException c) {
    		  System.out.println("Updated");
    		  ArrayList<Integer> list2 = new ArrayList<Integer>();
    		  for(int i = 0; i<mySheet.getWords().size();i++){  
    			  list2.add(0);			
    			}
    		  return list2;
    		  
    		}
    		//Verify list data
    		
    		return list;
	}
}
