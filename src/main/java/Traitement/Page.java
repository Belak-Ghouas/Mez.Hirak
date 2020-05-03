package Traitement;

public class Page {

	String title;
	 int id ;
	String text ;
	
	public Page(String title , int id , String text) {
		
		this.title=title;
		this.id=id;
		this.text=text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}

