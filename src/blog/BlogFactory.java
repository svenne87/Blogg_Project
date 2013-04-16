package blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class BlogFactory {
	
    private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private static String url = "jdbc:mysql://localhost:3306/blog";
	private static String user = "root";
	private static String password = "";
	
	
	//function to create and fill the blogSystem
	public static Blogg createBlogSystem(Blogg bloggSystem){
		bloggSystem = new Blogg();
	
		bloggSystem.setAuthors(BlogFactory.getAllAuthors());
		bloggSystem.setCategories(BlogFactory.getAllCategories());
		bloggSystem.setPosts(BlogFactory.getAllPosts(bloggSystem));
		
		return bloggSystem;
	}
	
	
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


	//add new author to database
	public static void insertAuthor(Author author)
	{

		try {
			con = DriverManager.getConnection(url, user, password);

			PreparedStatement prepStmt = con.prepareStatement("INSERT INTO authors (firstName, lastName, age, gender, country, mail) VALUES (?, ?, ?, ?, ?, ?)");
			prepStmt.setString(1, author.getFirstName());
			prepStmt.setString(2,  author.getLastName());
			prepStmt.setInt(3,  author.getAge());
			prepStmt.setString(4,  author.getGender());
			prepStmt.setString(5,  author.getCountry());
			prepStmt.setString(6,  author.getEmail());

			System.out.println(prepStmt.toString());
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// add new category to database
	public static void insertCategory(Category category) {

		try {
			con = DriverManager.getConnection(url, user, password);

			PreparedStatement prepStmt = con.prepareStatement("INSERT INTO categories (category) VALUES (?)");
			prepStmt.setString(1, category.getCategory());

			System.out.println(prepStmt.toString());
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	// add new post to database
	public static void insertPost(Post post) {

		try {
			con = DriverManager.getConnection(url, user, password);

			PreparedStatement prepStmt = con.prepareStatement("INSERT INTO post (title, content, date, authorId, categoryId) VALUES (?, ?, ?, ?, ?)");
			prepStmt.setString(1, post.getTitle());
			prepStmt.setString(2, post.getContent());
			prepStmt.setString(3, post.getDate());
			prepStmt.setInt(4, post.getAuthor().getId());
			prepStmt.setInt(5, post.getCategory().getId());

			System.out.println(prepStmt.toString());
			prepStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
