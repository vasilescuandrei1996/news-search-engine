import java.io.*;
import java.util.*;

public class RunApplication {
	
	
	
	private static void listStartMenu(){
		System.out.println("1)Add a new article");
		System.out.println("2)User Profile");
		System.out.println("0)Exit");
		System.out.println("Choose a command!");
	}
	
	private static void listProfileMenu(){
		System.out.println("1)Log in");
		System.out.println("2)Register");
		System.out.println("0)Back");
		System.out.println("Choose a command!");
	}
	
	private static void listQueryMenu(){
		System.out.println("1)List all articles published in the last month");
		System.out.println("2)List all articles published by a certain author / set of authors");
		System.out.println("3)List all articles  that have a certain word in their title");
		System.out.println("4)View all your queries");
		System.out.println("0)Back");
		
		
	}

	private static void addArticle(Scanner s){
		Article a = new Article();
		System.out.println("Insert the title:");
		a.setTitle(s.nextLine());
		System.out.println("Insert the author:");
		a.setAuthor(s.nextLine());
		System.out.println("Insert the text body:");
		a.setTextBody(s.nextLine());
		a.setPublishDate();
		Article.saveArticle(a, Article.fileName);
		System.out.println("Article added!");
	}
	
	
	public static void main(String[] args)  {
		Scanner s = new Scanner(System.in);
		String command;
		boolean test1 = true;
		while(test1 == true){
			listStartMenu();
			command = s.nextLine();
			switch(command){
			case "1":{addArticle(s);break;}
			case "2":{
				boolean test2 = true;
				etiquete:
				while(test2==true){
					listProfileMenu();
					command = s.nextLine();
					switch(command){
					case "1":{
						String username,password;
						System.out.println("Username: ");
						username=s.nextLine();
						System.out.println("Password: ");
						password=s.nextLine();
						if(Profile.canLogIn(username, password, Profile.fileName) == false){
							System.out.println("Username or password incorrect!");
							break;
						}
						else{
							boolean test3=true;
							System.out.println("Welcome " + username + "!");
							while(test3==true){
								listQueryMenu();
								command = s.nextLine();
								switch(command){
								case "1":{
									Query.listArticleFromDate();
									Query.saveQuery(username, "The articles published in the last month", Query.fileName);
									System.out.println("The above articles are the searched articles!\nThe query was saved!\nChoose another command!");
									break;
								}
								case "2":{
									System.out.println("Insert the author/set of authors!");
									System.out.println("For a set of authors insert them separated by a comma! (eg: Author1,Author2)");
									String authors = s.nextLine();
									Query.listArticleFromAuthor(authors);
									Query.saveQuery(username, "The articles published by " + authors, Query.fileName);System.out.println("The above articles are the searched articles!\nThe query was saved!\nChoose another command!");
									break;
								}
								case "3":{
									System.out.println("Insert a word for find articles that have that word in their title!");
									String word = s.nextLine();
									Query.listArticleFromTitle(word);
									Query.saveQuery(username, "The articles that have word " + "\"" + word + "\"" + " in title", Query.fileName);
									System.out.println("The above articles are the searched articles!\nThe query was saved!\nChoose another command!");
									break;
								}
								case "4":{
									Query.listQueriesFromUsername(username);
									System.out.println("Choose another command!");
									break;
								}
								case "0":{continue etiquete;}
								default:{
									System.out.println("Incorrect option! Try again!");
									break;
								}
								}
							}
						}
						
					}
					case "2":{
						Profile p = new Profile();
						System.out.println("Insert your profile information!");
						System.out.println("First Name: ");
						p.setFirstName(s.nextLine());
						System.out.println("Last Name: ");
						p.setLastName(s.nextLine());
						System.out.println("Username: ");
						while(p.setUsername(s.nextLine())==false){
							System.out.println("This username exists!");
							System.out.println("New username: ");
						}
						System.out.println("Password: ");
						while(p.setPassword(s.nextLine())==false){
							System.out.println("The password must contain more than 8 characters!");
							System.out.println("New password: ");
						}
						Profile.saveProfile(p, Profile.fileName);
						System.out.println("Successful registration");
						break;
					}
					case "0":{test2=false;break;}
					default:{
						System.out.println("Incorrect option! Try again!");
						break;
					}
					}
				}
				break;
			}
			case "0":{test1 = false;break;}
			default:{
				System.out.println("Incorrect option! Try again!");
				break;
			}
			}
		}
		
		s.close();
		
		System.out.println("The application was closed!");
		
		
	}
}
