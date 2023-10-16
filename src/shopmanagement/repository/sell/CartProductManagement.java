package shopmanagement.repository.sell;

import java.util.ArrayList;

import shopmanagement.dao.CartProduct;

public class CartProductManagement {
private static ArrayList<CartProduct> listProduct = new ArrayList<CartProduct>();
	
	public static ArrayList<CartProduct> getListProduct() {
		return listProduct;
	}

	public static void setListProduct(ArrayList<CartProduct> listProduct) {
		CartProductManagement.listProduct = listProduct;
	}
	
	public static CartProduct getProductListByID(int id, int idCart) {
		for(CartProduct x : listProduct) {
			if(x.getProductID() == id && x.getCartID() == idCart) {
				return x;
			}
		}
		return null;
	}
	
	public static int findProductInCartById(int id, int idCart) {
		for(int i=0; i<listProduct.size(); i++) {
			if(listProduct.get(i).getProductID() == id && listProduct.get(i).getCartID() == idCart) {
				return i;
			}
		}
		return -1;
	}
	

}
