package shopmanagement.dao;

public class CartProduct {
	private int cartID;
	private int productID;
	private int amount;
	public CartProduct() {
		super();
	}
	
	public CartProduct(int cartID, int productID, int amount) {
		super();
		this.cartID = cartID;
		this.productID = productID;
		this.amount = amount;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	
}
