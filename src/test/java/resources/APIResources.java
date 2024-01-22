package resources;

public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	AddBookAPI("/Library/Addbook.php"),
	GetBookApi("/Library/GetBook.php"),
	DeleteBookApi("/Library/DeleteBook.php"),
	AddProductAPI("/api/ecom/product/add-product"),
	CreateProductAPI("/api/ecom/order/create-order"),
	GetOrderDetailsAPI("/api/ecom/order/get-orders-details"),
	DeleteProductAPI("/api/ecom/product/delete-product/{productId}"),
	LoginEcomAPI("/api/ecom/auth/login"),
	DeleteOrderAPI("/api/ecom/order/delete-order/{orderid}");
	private String resources;
	
	APIResources(String resources){
		this.resources=resources;
	}
	public  String getResources() {
		return resources;
	}
}
