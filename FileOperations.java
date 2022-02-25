package mypackage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class FileOperations {
	
	//this method lays out all the possible File Operations
	void userMenu()
	{
		try {
				int ch;
				System.out.println("What File Operation would you like to perform?\n");
				System.out.println("1. Create a File");
				System.out.println("2. Delete a File");
				System.out.println("3. Search a File");
				System.out.println("4. Return to Main Menu\n");
				System.out.println("Enter your choice : ");
				Scanner sc = new Scanner(System.in);
				ch = Integer.parseInt(sc.nextLine());
				switch(ch) 
				{
					case 1: addFile();
					break;
					case 2: deleteFile();
					break;
					case 3: searchFile();
					break;
					case 4: Menu.main(null);
					break;
					default:System.err.println("Invalid option. Please enter either 1 or 2 or 3 or 4.");
					userMenu();
					break;
				}
				sc.close();
		}catch(NumberFormatException ex) {
        	System.err.println("Please enter only numerical value");
        	userMenu();
		}
	}
	
	
	//this method allows you to add a User specified file
	void addFile()
	{
		String filepath = ("D:\\MyLocker\\");
		Scanner sc=new Scanner(System.in);
    	
    	
    	System.out.println("Enter file name : ");
    	String path = sc.nextLine();
    	
    	
        File f = new File(filepath + path);
      	
        
        // Create new file if it does not exist
        try {
			if (f.createNewFile())
			{
			    System.out.println("File created successfully!");
			    userMenu();
			}
			else
			{
			    System.out.println("File already exists!");
			    userMenu();
			}
		} catch (IOException e) {
			System.err.println("Unable to create file. Please contact Admin!");
			userMenu();
			}
			catch (SecurityException e) {
				System.err.println("Unable to create file. Please contact Admin!");
				userMenu();
		}
        sc.close();
	}
	
	
	//this method allows you to delete a User specified file
	void deleteFile()
	{
		try {
				String filepath = ("D:\\MyLocker\\");
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter file name : ");
				String path = sc.nextLine();
				
				File f=new File(filepath + path);
				
				if(f.getAbsolutePath().equals(f.getCanonicalPath()))
					{
						if(f.delete())   
						{
							System.out.println("File deleted successfully");
							userMenu();
						}
						else
						{
							System.err.println("Unable to execute delete operation");
							userMenu();
						}
				}
				else
				{
					System.out.println("File not available");
					userMenu();
				}
				sc.close();
				
		}catch(SecurityException | IOException ex) {
		System.err.println("Error occurred while deleting file. Please contact Admin.");
		userMenu();
		}
		
	}
	
	
	//this method allows you to search a User specified file
	void searchFile()
	{
		String filepath = ("D:\\MyLocker\\");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter file name : ");
		String path = sc.nextLine();
		
		File f = new File(filepath + path);
		
				try {
						if(f.getAbsolutePath().equals(f.getCanonicalPath()))
						{
							System.out.println("File exists");
							userMenu();
						}
						else
						{
							System.out.println("File does not exist : " + path);
							userMenu();
						}
					} catch (IOException e) {
					
					System.err.println("Unable to perform search operation. Please contact Admin!");
					userMenu();
				}
			
		sc.close();
	}

}
