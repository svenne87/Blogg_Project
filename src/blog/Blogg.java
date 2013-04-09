package blog;
import java.util.ArrayList;
import java.util.List;

public class Blogg {
	private List<Post> posts = new ArrayList<Post>();
	private List<Author> authors = new ArrayList<Author>();
	private List<Category> categories = new ArrayList<Category>();
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void addToList(Post post){
	  	posts.add(post);
	}
	
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Post getFromList(int i){
		return posts.get(i);
	}
	
	public int getListSize(){
		return posts.size();
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	
	
}
