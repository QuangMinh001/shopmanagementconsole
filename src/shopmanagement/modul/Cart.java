package shopmanagement.modul;

import java.util.ArrayList;
import java.util.Scanner;

import shopmanagement.dao.CartProduct;
import shopmanagement.repository.sell.CartManagement;
import shopmanagement.repository.sell.CartProductManagement;
import shopmanagement.repository.update.CustomerManagement;
import shopmanagement.repository.update.ProductManagement;

public class Cart implements BaseModel{
	private int id;
	private int customerID;
	private ArrayList<Integer> listProduct;

	public static Scanner sc = new Scanner(System.in);

	@Override
	public void display() {
		System.out.println("\nID gio hang: " + this.id);
		System.out.println("Khach hang: " + CustomerManagement.getCustomerById(this.customerID).getName());
		int index = CustomerManagement.findCustomerById(this.customerID);
		if (index == -1) {
			System.out.println("Khach hang khong co trong danh sach");
			return;
		}
		int stt = 1;

		System.out.println("--------------San pham co trong gio hang---------------");
		if (listProduct.size() == 0) {
			System.out.println("Gio hang chua co san pham, them san pham vao gio");
			return;
		}
		System.out.printf("%3s %4s %-30s %9s %10s%n", "STT", "ID", "Ten san pham", "So luong", "Don gia");
		for (int i = 0; i < listProduct.size(); i++) {
			Product product = ProductManagement.getProductById(listProduct.get(i));
			CartProduct productList = CartProductManagement.getProductListByID(listProduct.get(i), this.id);
			System.out.printf("%3d %4s %-30s %9s %10.2f%n", stt++, listProduct.get(i), product.getName(),
					productList.getAmount(), product.getPrice());
		}
		System.out.printf("\t\t\tTong tien phai thanh toan: %10.2f%n", this.tongTien());
	}

	public double tongTien() {
		double tongTien = 0;
		for (int i = 0; i < listProduct.size(); i++) {
			Product product = ProductManagement.getProductById(listProduct.get(i));
			CartProduct cartProduct = CartProductManagement.getProductListByID(listProduct.get(i), this.id);
			tongTien += cartProduct.getAmount() * product.getPrice();
		}
		return tongTien;
	}

	@Override
	public void update() {
		do {
			System.out.println("\n\n-----------Cap nhat gio hang-------------");
			System.out.println("\t1. Xem gio hang");
			System.out.println("\t2. Them san pham vao gio");
			System.out.println("\t3. Sua thong tin san pham trong gio");
			System.out.println("\t4. Xoa san pham khoi gio");
			System.out.println("\t5. Thanh toan");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				display();
				break;
			case 2:
				addProduct();
				break;
			case 3:
				updateProduct();
				break;
			case 4:
				removeProduct();
				break;
			case 5:
				payment();
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		} while (true);
	}

	private void addProduct() {
		System.out.println("\n-----------Them san pham vao gio hang---------------");
		System.out.print("Nhap id san pham: ");
		int _productId = Integer.parseInt(sc.nextLine());
		int index = ProductManagement.findProductById(_productId);
		if (index == -1) {
			System.out.println("San pham khong co trong danh sach");
			return;
		}
		Product x = ProductManagement.getProductById(_productId);
		int check = CartProductManagement.findProductInCartById(_productId, this.id);
		if (check != -1) {
			System.out.println("San pham da co trong danh sach");
			return;
		}
		System.out.print("Nhap so luong san pham: ");
		int _amount = Integer.parseInt(sc.nextLine());

		if (_amount > x.getAmount()) {
			System.out.println("Khong du san pham");
			return;
		}
		CartProductManagement.getListProduct().add(new CartProduct(this.id, _productId, _amount));
		listProduct.add(_productId);
		System.out.println("Them san pham vao gio thanh cong");

	}

	private void updateProduct() {
		System.out.println("\n--------------Sua thong tin san pham trong gio---------------");
		System.out.print("Nhap id san pham: ");
		int id = Integer.parseInt(sc.nextLine());
		Product x = ProductManagement.getProductById(id);
		int index = CartProductManagement.findProductInCartById(id, this.id);
		if (index == -1) {
			System.out.println("San pham chua co trong gio hang");
			return;
		}
		System.out.print("Nhap so luong san pham can sua: ");
		int _amount = Integer.parseInt(sc.nextLine());
		if (_amount > x.getAmount()) {
			System.out.println("Khong du so luong san pham");
			return;
		}
		CartProductManagement.getListProduct().get(index).setAmount(_amount);
		System.out.println("Cap nhat so luong thanh cong");
	}

	private void removeProduct() {
		System.out.println("\n--------------Xoa san pham trong gio----------------------");
		System.out.print("Nhap id san pham: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = CartProductManagement.findProductInCartById(id, this.id);
		if (index == -1) {
			System.out.println("San pham chua co trong gio hang");
			return;
		}
		CartProductManagement.getListProduct().remove(index);
		listProduct.remove((Integer) id);
		System.out.println("Xoa san pham trong gio thanh cong");
	}

	private void payment() {
		ArrayList<CartProduct> cartProducts = CartProductManagement.getListProduct();
		CartManagement.getPayedCarts().add(this);
		for (CartProduct cartProduct : cartProducts) {
			Product _productDb = ProductManagement.getProductById(cartProduct.getProductID());
			_productDb.setAmount(_productDb.getAmount() - cartProduct.getAmount());
		}
		System.out.printf("Thanh toan thanh cong gio hang[%d] %n", this.id);
	}

	public Cart() {
		super();
	}

	public Cart(int id, int customerID, ArrayList<Integer> listProduct) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.listProduct = listProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public ArrayList<Integer> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<Integer> listProduct) {
		this.listProduct = listProduct;
	}

}
