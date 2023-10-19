package resources;

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	AddBookAPI("/Library/Addbook.php"),
	GetBookApi("/Library/GetBook.php"),
	DeleteBookApi("/Library/DeleteBook.php");
	
	private String resources;
	
	APIResources(String resources){
		this.resources=resources;
	}
	public  String getResources() {
		return resources;
	}
}
