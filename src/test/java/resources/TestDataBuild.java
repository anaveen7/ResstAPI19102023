package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddBook;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name,String language,String address) {
		AddPlace a= new AddPlace();
		a.setAccuracy(50);
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress(address);
		a.setWebsite("http://google.com");
		a.setLanguage(language);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setTypes(myList);
		a.setLocation(l);
		return a;
	}
	
	public String deletePlacePayLoad(String PlaceId) {
		return "{\r\n    \"place_id\":\""+PlaceId+"\"\r\n}";
	}
	
	public AddBook addBook(String Isbn,int Aisle) {
		AddBook ab = new AddBook();
		ab.setName("Learn Appium Automation with Java");
		ab.setIsbn(Isbn);
		ab.setAisle(Aisle);
		ab.setAuthor("Naveen");
		return ab;
	}
	
	public String deleteBook(String ID) {
		return "{\"ID\":\""+ID+"\"}";
	}

}
