package blog;
public class Category {
	
	private int id;
	private String category;

	
	public int getId(){
		return id;
	}
	
	public void setId(int id_in){
		id = id_in;
	}
	
	public void setValues(String category_in){
		category = category_in;
	}
	
	public String getCategory(){
		return category;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + "]";
	}
	
}
