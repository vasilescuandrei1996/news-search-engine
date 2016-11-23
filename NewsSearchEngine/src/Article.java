import java.io.*;
import java.util.*;

public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static transient String fileName = "articles.ser";//file where the articles will be saved
	private String author;
	private Date publishDate;
	private String title;
	private String textBody;
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public void setPublishDate(){
		publishDate = new Date();
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setTextBody(String textBody){
		this.textBody = textBody;
	}
	
	public String getAuthor(){
		return this.author;
	}
	
	public Date getPublishDate(){
		return this.publishDate;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getTextBody(){
		return this.textBody;
	}
	
	//method for save an article in the file
	public static void saveArticle(Article article,String fileName){
		
		ArrayList<Article> listOfArticles = null;
		try{
			FileInputStream fIn = new FileInputStream(fileName);
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
			
			try{
				FileOutputStream fout = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				listOfArticles.add(article);
				out.writeObject(listOfArticles);
				out.flush();
				fout.close();
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
}
	//method for list all articles on the screen
	public static void listAllArticles(String fileName) throws IOException,ClassNotFoundException{
			
			FileInputStream fIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fIn);
			ArrayList<Article> listOfArticles = (ArrayList<Article>)in.readObject();
			in.close();
			if(listOfArticles != null){
				for(int i=0;i<listOfArticles.size();i++){
					System.out.println(listOfArticles.get(i).getTitle() + ' ' + listOfArticles.get(i).getAuthor() + ' ' + listOfArticles.get(i).getTextBody() + ' ' + listOfArticles.get(i).getPublishDate());
				}
			}
			
	}
	
	//method for list a given article
	public static void listArticle(Article article){
		System.out.println("Title: " + article.getTitle());
		System.out.println("Author: " + article.getAuthor());
		System.out.println("Text body: ");
		System.out.println(article.getTextBody());
		System.out.println("Publish date: " + article.getPublishDate() + '\n');
	}

}
