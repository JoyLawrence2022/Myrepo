package mypackage;

import java.io.File;
import java.util.*;

public class Menu {
	public static void main(String[] args) {
		createDir();
		new Menu();		
	}
	
	//this method displays the Welcome Menu and all available operations
	public void displayMenu() 
	{
		System.out.println("\t    Welcome to LockedMe");
		System.out.println("\t    ===================");
		System.out.println("\tDeveloped by : Joy Lawrence\n\n\n");
		System.out.println("\t\t Menu");
		System.out.println("\t\t=====\n");
		System.out.println("\t1.  Display all files");
		System.out.println("\t2.  File Operations");
		System.out.println("\t3.  Exit\n");
		System.out.println("Enter your choice: ");
		
	}
	
	Menu() 
    {
		Scanner sc = new Scanner(System.in);
        displayMenu();
        try {
        int ch = Integer.parseInt(sc.nextLine());
       
  
		switch (ch) 
		{
		    case 1: new ListOfFiles().retrieveList();   //shows all the available files in Directory
		    new Menu();
		    break;
	  
		    case 2: new FileOperations().userMenu();    //shows the Menu to do various operations on files
		    break;
	  
		    case 3:										//this option allows User to exit Application
		    System.out.println ( "You have now exited the Application!" ); 
		    System.exit(0);
		    break;
	
		    default: 
		    System.err.println ( "Unrecognized option. Enter either 1 or 2 or 3.\n" );
		    new Menu();
		    break;
		}
        }catch(NumberFormatException ex) {
        	System.err.println("Please enter only numerical value");
        	new Menu();
        }
        sc.close();
    }
	
	static void createDir() 
	{
		File file=new File("D:\\MyLocker");
		file.mkdir();

	}

}
