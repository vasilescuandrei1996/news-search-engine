import java.io.*;
import java.util.*;

public class Query implements Serializable {
	
	private static final long serialVersionUID = -1080818252671059038L;
	public static transient String fileName = "queries.ser";//file where the queries will be saved
	private String username;
	private Date queryDate;
	private String queryType;
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public void setQueryDate(){
		this.queryDate = new Date();
	}
	
	public void setQueryType(String queryType){
		this.queryType = queryType;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public Date getQueryDate(){
		return this.queryDate;
	}
	
	public String getQueryType(){
		return queryType;
	}
	
	//method which list the articles that have a certain word in the title
	public static void listArticleFromTitle(String word){
		ArrayList<Article> listOfArticles = null;
		try{
			FileInputStream fIn = new FileInputStream(Article.fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfArticles = (ArrayList<Article>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		if( listOfArticles != null){
			
			for(int i=0;i<listOfArticles.size();i++){
				if(listOfArticles.get(i).getTitle().contains(word) == true){
					Article.listArticle(listOfArticles.get(i));
				}
			}
			
		}
	}
	
	//method which list all the articles publish by an author/set of authors
	public static void listArticleFromAuthor(String authors){
		ArrayList<Article> listOfArticles = null;
		try{
			FileInputStream fIn = new FileInputStream(Article.fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfArticles = (ArrayList<Article>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		if( listOfArticles != null){
			
			String[] author = authors.split(",");
			
			for(int i=0;i<listOfArticles.size();i++){
				for(int j=0;j<author.length;j++){
					if(listOfArticles.get(i).getAuthor().equals(author[j]) == true){
						Article.listArticle(listOfArticles.get(i));
					}
				}
			}
			
		}
	}
	
	//method which list all the articles from the last month
	public static void listArticleFromDate(){
		ArrayList<Article> listOfArticles = null;
		try{
			FileInputStream fIn = new FileInputStream(Article.fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfArticles = (ArrayList<Article>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		if( listOfArticles != null){
			
			Date d = new Date();
			int year=d.getYear();
			int month=d.getMonth();
			if(month==1){
				d.setYear(year-1);
				d.setMonth(12);
			}
			else{
				d.setMonth(month-1);
			}
			for(int i=0;i<listOfArticles.size();i++){
				if(listOfArticles.get(i).getPublishDate().after(d)==true){
					Article.listArticle(listOfArticles.get(i));
				}
			}
		}
	}
	
	//method which list all the queries saved by an user
	public static void listQueriesFromUsername(String username){
		
		ArrayList<Query> listOfQueries = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfQueries = (ArrayList<Query>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		if(listOfQueries != null){
			Query q;
			int contor=0;
			for(int i=0;i<listOfQueries.size();i++){
				q=listOfQueries.get(i);
				if(q.getUsername().equals(username)==true){
					contor = contor+1;
					System.out.println("Date: " + q.getQueryDate() + "    Type: " + q.getQueryType());
				}
			}
			if(contor==0){
				System.out.println("You haven't queries.");
			}
			else{
				System.out.println("You have " + contor + " queries.");
			}
		}
		
	}

//method which save the query in the file
public static void saveQuery(String username,String type,String fileName){
		
		ArrayList<Query> listOfQueries = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			try{
				listOfQueries = (ArrayList<Query>)in.readObject();
			}
			catch(ClassNotFoundException e){
				System.err.println(e);
			}
			in.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
		
		
		if( listOfQueries != null){
			
			try{
				Query query = new Query();
				query.setUsername(username);
				query.setQueryType(type);
				query.setQueryDate();
				FileOutputStream fout = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				listOfQueries.add(query);
				out.writeObject(listOfQueries);
				out.flush();
				fout.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
}

//method for list all queries
public static void listAllQueries(String fileName) throws IOException,ClassNotFoundException{
	
	FileInputStream fIn = new FileInputStream(fileName);
	ObjectInputStream in = new ObjectInputStream(fIn);
	ArrayList<Query> listOfQueries = (ArrayList<Query>)in.readObject();
	in.close();
	if(listOfQueries != null){
		for(int i=0;i<listOfQueries.size();i++){
			System.out.println(listOfQueries.get(i));
		}
	}
	
}
}
