package blog;

import java.util.Date;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class Post {

	private String content;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date date = new Date();
	private String title;
	public Author author;
	public Category category;

	public void SetValues(String title_in, String content_in, Date date_in) {
		title = title_in;
		content = content_in;
		date = date_in;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the super title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String GetContent() {
		return content;
	}

	public String GetDate() {
		return dateFormat.format(date);
	}

	public String GetBloggPost() {
		return "Författare: " + author.GetName() + "\r\n" + "Kategori: "
				+ category.GetCategory() + "\r\n" + title + "\r\n" + GetDate()
				+ "\r\n" + content;
	}

}
