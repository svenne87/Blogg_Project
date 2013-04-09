package blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BlogFactory {
	
    private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private static String url = "jdbc:mysql://localhost:3306/blog";
	private static String user = "root";
	private static String password = "";
	
	
	
	//returns the category with that id
	public static Category getCategory(int id) {
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM categories WHERE id=" + id);

			if (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setValues(rs.getString("category"));
				
				return category;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	//returns the author with that id
	public static Author getAuthor(int id) {
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM authors WHERE id=" + id);

			if (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setValues(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("country"), rs.getString("mail"));
				
				return author;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	//returns the author with that id
	public static Post getPost(int id, Blogg bloggSystem) {
		
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM post WHERE id=" + id);

			if (rs.next()) {

				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setValues(rs.getString("title"), rs.getString("content"), rs.getDate("date"));
				
				//Authors
				if ( bloggSystem.getAuthors().size() == 0 ) {
					bloggSystem.setAuthors(getAllAuthors());
				}
				
				for (Author author : bloggSystem.getAuthors()) {
					if ( author.getId() == rs.getInt("authorId") ) {
						post.setAuthor(author);
					}
				}
				
				
				//Categories
				if (bloggSystem.getCategories().size() == 0){
					bloggSystem.setCategories(getAllCategories());
				}
				
			    for (Category category : bloggSystem.getCategories()){
			    	if ( category.getId() == rs.getInt("categoryId") ){
			    		post.setCategory(category);
			    	}
			    }
				
				
				return post;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	//returns a list with all authors
	public static List<Author> getAllAuthors() {
		List<Author> returnList = new ArrayList<Author>();

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM authors");

			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setValues(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("country"), rs.getString("mail"));
				returnList.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnList;
	}
	
	
	
	//returns a list with all categories
	public static List<Category> getAllCategories() {
		List<Category> returnList = new ArrayList<Category>();

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM categories");

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setValues(rs.getString("category"));
				returnList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnList;
	}
	
	
	
	//returns a list with all posts
	public static List<Post> getAllPosts(Blogg bloggSystem) {
		List<Post> returnList = new ArrayList<Post>();

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM post");

			while (rs.next()) {
				
				Post post = new Post();
				post.setId(rs.getInt("id"));
				post.setValues(rs.getString("title"), rs.getString("content"), rs.getDate("date"));
				

				//Authors
				if ( bloggSystem.getAuthors().size() == 0 ) {
					bloggSystem.setAuthors(getAllAuthors());
				}
				
				for (Author author : bloggSystem.getAuthors()) {
					if ( author.getId() == rs.getInt("authorId") ) {
						post.setAuthor(author);
					}
				}
				
				
				//Categories
				if (bloggSystem.getCategories().size() == 0){
					bloggSystem.setCategories(getAllCategories());
				}
				
			    for (Category category : bloggSystem.getCategories()){
			    	if ( category.getId() == rs.getInt("categoryId") ){
			    		post.setCategory(category);
			    	}
			    }
				
				
				returnList.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnList;
	}


	
	

}
