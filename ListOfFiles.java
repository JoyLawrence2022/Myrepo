package mypackage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ListOfFiles {
	
	//this method allows User to view all available files in the Directory
	void retrieveList() {
		int i;
		try{
				File f = new File("D:\\MyLocker");
			
				String[] fileList1 = f.list();
				
				List<String> list1=new ArrayList<String>();
				
				for(i=0;i<fileList1.length;i++)
				{
					list1.add(fileList1[i]);
				}
				
				list1.sort(null);  //this command sorts the values in the list
						
				System.out.println(list1);
			}catch(SecurityException ex) 
			{
				System.err.println("Unable to read Directory. Please contact Admin!");
				new Menu();
			}
	}
	
}
