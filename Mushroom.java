import java.util.*;

public class Mushroom{
	String category;
	ArrayList<String> attributes;
	
	public Mushroom(){
		attributes=new ArrayList<String>();}
		
	public String getCategory(){
		return category;}
		
	public ArrayList<String> getAttributes(){
		return attributes;}
		
	public void addAttribute(String attribute){
		attributes.add(attribute);}
		
	public void setCategory(String category){
		this.category=category;
	}
}
