import java.io.*;
import java.util.ArrayList;

public class Profile implements Serializable {
	
	private static final long serialVersionUID = 4732299492525289412L;
	public static transient String fileName = "profiles.ser";//file where the profiles will be saved
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	//setter which verify if the given username exists or not
	public boolean setUsername(String username){
		if(Profile.NotExistUsername(username) == true){
			this.username = username;
			return true;
		}
		else 
			return false;
	}
	
	//setter which verify if the password respects the lengh
	public boolean setPassword(String password){
		if(password.length()>=8){
			this.password = password;
			return true;
		}
		else return false;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	//method which verify if the username exists or not
	public static boolean NotExistUsername(String username){
		
		ArrayList<Profile> listOfProfiles = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfProfiles = (ArrayList<Profile>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		if(listOfProfiles != null){
			for(int i=0;i<listOfProfiles.size();i++){
				if(listOfProfiles.get(i).getUsername().equals(username))
					return false;
			}
		}
		return true;
	}
	
	//method which verify if the username and password are correct
	public static boolean canLogIn(String username,String password,String fileName){
		
		ArrayList<Profile> listOfProfiles = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfProfiles = (ArrayList<Profile>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		if(listOfProfiles != null){
			for(int i=0;i<listOfProfiles.size();i++){
				if(listOfProfiles.get(i).getUsername().equals(username)&&
						listOfProfiles.get(i).getPassword().equals(password))
					return true;
			}
		}
		
		return false;
		
	}
	
//method which save the profile in the file
public static void saveProfile(Profile profile,String fileName){
		
		ArrayList<Profile> listOfProfiles = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfProfiles = (ArrayList<Profile>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		if( listOfProfiles != null){
			
			try{
				FileOutputStream fout = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				listOfProfiles.add(profile);
				out.writeObject(listOfProfiles);
				out.flush();
				fout.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}

//method which list all profiles on the screen
public static void listAllProfiles(String fileName) throws IOException,ClassNotFoundException{
	
	FileInputStream fIn = new FileInputStream(fileName);
	ObjectInputStream in = new ObjectInputStream(fIn);
	ArrayList<Profile> listOfProfiles = (ArrayList<Profile>)in.readObject();
	in.close();
	if(listOfProfiles != null){
		for(int i=0;i<listOfProfiles.size();i++){
			System.out.println(listOfProfiles.get(i).getFirstName() + ' ' + listOfProfiles.get(i).getLastName() + ' ' + listOfProfiles.get(i).getUsername() + ' ' + listOfProfiles.get(i).getPassword());
		}
	}
	
	

}
}
