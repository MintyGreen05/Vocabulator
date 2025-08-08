package main;
import java.util.*;
import java.io.*;



public class Pool {

	private static BufferedReader a;
	private static PrintWriter w;
	private static String File="daVacabulatorium.csv";
	private static ArrayList<Sheet> sheets = new ArrayList<Sheet>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static User currentUser;
	private static int numOfSheets = 0;
	
	public static void readSheets()throws IOException{
		a = new BufferedReader(new FileReader(File));
		ArrayList<Word> tempWords = new ArrayList<Word>();
		String s="";
		int c = 0;
		while(!(s=a.readLine()).equals("START OF USERS")){
			if (s.equals("-")){
				s=a.readLine();
				numOfSheets++;
				sheets.add(new Sheet(numOfSheets,s,tempWords,c));
				tempWords.clear();
				c =0;
			}else if (s.equals("x")){
				sheets.get(numOfSheets-1).makeInactive();
			}else{
				String[] b=s.split(",");
				System.out.println(s);
				tempWords.add(new Word(b[0],b[1],b[2],b[3]));
				c++;
			}
				
		}
		
		int id = 0;
		
		while((s=a.readLine())!=null){
			
			User aUser = new User(++id,s);
			
			users.add(aUser);
			aUser.addSheetData(sheets);
			
			
		}
		
		System.out.println("success");
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		
		
		String command = "0"; 
		readSheets();
		Scanner sc = new Scanner(System.in);
		while(!command.equalsIgnoreCase("/exit")){
			System.out.println("Log in");
			command = sc.nextLine();
			int m = -1;
			for(int i = 0; i<users.size();i++){
				if(users.get(i).getName().equals(command)){
					m=i;
					break;
				}
			}
			if(m!=-1){
				currentUser = users.get(m);
				System.out.println("Mastery Level: " + currentUser.getMasteryLevel());
				while(!command.equalsIgnoreCase("/exit")){
					System.out.println("L1,L2,Random,/exit,/save,/update_sheet");
				    command = sc.nextLine();
				    if(command.equals("/exit")){
				    	break;
				    }
				    	
				    int pr;
						switch(command){
						case "/update_sheet":
							readSheets();
							break;
						case "/save":
							currentUser.saveSheetData();
							break;
						case "L2":
							pr = pickSheet();
		                	if(pr== -1)
		                		break;
							base(sheets.get(pr),currentUser,2);
							break;
		                case "L1":
		                	pr = pickSheet();
		                	if(pr== -1)
		                		break;
							base(sheets.get(pr),currentUser,1);
							break;
		                case "Random":
		                	pr = pickSheet();
		                	if(pr== -1)
		                		break;
							base(sheets.get(pr),currentUser,3);
		                	break;
						default:
							
						}
						command = "1";
				}
				command = "2";
			}
			
			
		}
		for(int i = 0; i<users.size();i++){
			users.get(i).saveSheetData();
		}

		
	   
	}
	public static int pickSheet(){
		int pr;
		printSheetNames();
		System.out.println("enter sheet name");
		Scanner sc = new Scanner(System.in);
    	String input = sc.nextLine();
    	pr = getSheetIndex(input);
    	return pr;
	}
	public static void printSheetNames(){
		System.out.print("Sheet names: ");
		for(int i = 0; i<numOfSheets;i++){
			if(sheets.get(i).isActive())
				System.out.print(sheets.get(i).getName()+" ");
		}
			
		System.out.println();
	}
	
	public static int getSheetIndex(String name){
		Scanner sc = new Scanner(System.in);
		String command = name;
		while(!command.equals("/exit")){
			for(int i = 0; i<sheets.size();i++){
				if(sheets.get(i).getName().equals(command)&&sheets.get(i).isActive()){
					return i;
				}
			}
			System.out.println("doesn't exist, try again");
			printSheetNames();
			command = sc.nextLine();
			
		}
		return -1;
		
			
	}
	
	public static void prEG(Sheet sheet, User myUser){
		System.out.println(sheet.getName());
		Scanner sc = new Scanner(System.in);
		String input = "0" ;
		Random rand = new Random();
		int rand1;
		while(!input.equalsIgnoreCase("/exit")){
			rand1 = rand.nextInt(sheet.getSize());
			Word myWord = sheet.getWords().get(rand1);
			System.out.println(myWord.getAnswer());
			input = sc.nextLine();
			System.out.println(myWord.getArtikel() + " " + myWord.getWord() + " " + myWord.getPrular() );
			input = sc.nextLine();
			while(true){
				if(input.equals("y")){
					myUser.inc_cEG(rand1, sheet.getSheetNum()-1);
					break;
				}else if(input.equals("n")){
					myUser.inc_mEG(rand1, sheet.getSheetNum()-1);
					break;
				}
				input = sc.nextLine();
			}
			myUser.print_EG(rand1, sheet.getSheetNum()-1);;
			System.out.println("--------------");
			input = sc.nextLine();
		}

	}
		public static void prGE(Sheet sheet, User myUser){
			System.out.println(sheet.getName());
			Scanner sc = new Scanner(System.in);
			String input = "0" ;
			Random rand = new Random();
			int rand1;
			while(!input.equalsIgnoreCase("/exit")){
				rand1 = rand.nextInt(sheet.getSize());
				Word myWord = sheet.getWords().get(rand1);
				System.out.println(myWord.getArtikel() + " " + myWord.getWord() + " " + myWord.getPrular()  );
				input = sc.nextLine();
				System.out.println(myWord.getAnswer());
				input = sc.nextLine();
				while(true){
					if(input.equals("y")){
						myUser.inc_cGE(rand1, sheet.getSheetNum()-1);
						break;
					}else if(input.equals("n")){
						myUser.inc_mGE(rand1, sheet.getSheetNum()-1);
						break;
					}
					input = sc.nextLine();
				}
				myUser.print_GE(rand1, sheet.getSheetNum()-1);;
				System.out.println("--------------");	
				input = sc.nextLine();
		}

	}
		public static void printSheets(){
			for(int i = 0; i<numOfSheets;i++){
				if(sheets.get(i).isActive())
					System.out.print(i+"."+sheets.get(i)+" ");
			}
				
			System.out.println();
		}

		public static ArrayList<Sheet> getSheets() {
			return sheets;
		}
		
		public static void wordEG(Sheet sheet, User myUser, int index){
			Word myWord = sheet.getWords().get(index);
			Scanner sc = new Scanner(System.in);
			String input;
			System.out.println(myWord.getAnswer());
			input = sc.nextLine();
			System.out.println(myWord.getArtikel() + " " + myWord.getWord() + " " + myWord.getPrular() );
			input = sc.nextLine();
			while(true){
				if(input.equals("y")){
					myUser.inc_cEG(index, sheet.getSheetNum()-1);
					break;
				}else if(input.equals("n")){
					myUser.inc_mEG(index, sheet.getSheetNum()-1);
					break;
				}
				input = sc.nextLine();
			}
			myUser.print_EG(index, sheet.getSheetNum()-1);
			System.out.println(" "+myUser.getComp_EG(sheet.getSheetNum()-1)+"%");
			System.out.println("--------------");
		}
        public static void wordGE(Sheet sheet, User myUser, int index){
        	Word myWord = sheet.getWords().get(index);
        	Scanner sc = new Scanner(System.in);
			String input;
			System.out.println(myWord.getArtikel() + " " + myWord.getWord() + " " + myWord.getPrular()  );
			input = sc.nextLine();
			System.out.println(myWord.getAnswer());
			input = sc.nextLine();
			while(true){
				if(input.equals("y")){
					myUser.inc_cGE(index, sheet.getSheetNum()-1);
					break;
				}else if(input.equals("n")){
					myUser.inc_mGE(index, sheet.getSheetNum()-1);
					break;
				}
				input = sc.nextLine();
			}
			myUser.print_GE(index, sheet.getSheetNum()-1);
			System.out.println(" "+myUser.getComp_GE(sheet.getSheetNum()-1)+"%");
			System.out.println("--------------");	
		}
        
		public static void base(Sheet sheet, User myUser, int mode){
			System.out.println(sheet.getName());
			Scanner sc = new Scanner(System.in);
			String input = "0" ;
			int index;
			if(mode!=3){
				index = indexPicker(sheet,myUser,mode);
			}else{
				Random rand = new Random();	
				index = rand.nextInt(sheet.getSize());	
			}
			if(mode == 1){
				while(!input.equalsIgnoreCase("/exit")){
					index = indexPicker(sheet,myUser,mode);
					wordGE(sheet,myUser,index);
					
					input = sc.nextLine();
				}
			}else if(mode == 2){
				while(!input.equalsIgnoreCase("/exit")){
					index = indexPicker(sheet,myUser,mode);
					wordEG(sheet,myUser,index);
					
					input = sc.nextLine();
				}
			}else{
				while(!input.equalsIgnoreCase("/exit")){
					index = indexPicker(sheet,myUser,mode);
					Random rand = new Random();	
					int r = rand.nextInt(1);
					if(r==1){
						wordEG(sheet,myUser,index);
					}else{
						wordGE(sheet,myUser,index);
					}				
					input = sc.nextLine();
				}
			}
			
			
			
		}
		public static int indexPicker(Sheet sheet, User myUser,int mode){
			Random rand = new Random();
			int index;
			index = rand.nextInt(sheet.getSize());
			if(mode==1){
				if(myUser.getComp_GE(sheet.getSheetNum()-1)==100){
					return index;
				}
				
				while(myUser.getCorrect_GE(index, sheet.getSheetNum()-1)>=3 
						&& myUser.getCorrect_GE(index, sheet.getSheetNum()-1)>= myUser.getMistake_GE(index, sheet.getSheetNum()-1)){
					index = rand.nextInt(sheet.getSize());
					
				}
				return index;
			}else{
				if(myUser.getComp_EG(sheet.getSheetNum()-1)==100){
					return index;
				}
				while(myUser.getCorrect_EG(index, sheet.getSheetNum()-1)>=2 
						&& myUser.getCorrect_EG(index, sheet.getSheetNum()-1)>= myUser.getMistake_EG(index, sheet.getSheetNum()-1)){
					index = rand.nextInt(sheet.getSize());
				}
				return index;
			}
			
		}
		public static void test(Sheet sheet, User myUser){
			
		}

		
	
}
