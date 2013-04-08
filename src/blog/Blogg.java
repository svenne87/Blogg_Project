package blog;
import java.util.ArrayList;
import java.util.List;

public class Blogg {
	private List<Post> posts = new ArrayList<Post>();
	
	public void AddToList(Post post){
	  	posts.add(post);
	}
	
	public Post GetFromList(int i){
		return posts.get(i);
	}
	
	public int GetListSize(){
		return posts.size();
	}
	
}
