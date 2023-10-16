package shopmanagement.repository.sell;

import java.util.ArrayList;
import java.util.Scanner;

import shopmanagement.modul.Cart;
import shopmanagement.modul.Customer;
import shopmanagement.repository.update.CustomerManagement;

public class CartManagement {
	private static int autoId = 1;
	public static Scanner sc = new Scanner(System.in);

	private static ArrayList<Cart> listCarts = new ArrayList<Cart>();

	private static ArrayList<Cart> payedCarts = new ArrayList<Cart>();

	public static ArrayList<Cart> getListCarts() {
		return listCarts;
	}

	public static void setListCarts(ArrayList<Cart> listCarts) {
		CartManagement.listCarts = listCarts;
	}

	public static ArrayList<Cart> getPayedCarts() {
		return payedCarts;
	}

	public static void setPayedCarts(ArrayList<Cart> payedCarts) {
		CartManagement.payedCarts = payedCarts;
	}

	public static void execute() {
		do {
			System.out.println("\n\n-------------Quan ly ban hang------------------");
			System.out.println("Chon chuc nang quan ly: ");
			System.out.println("\t1. Tao 1 gio hang");
			System.out.println("\t2. Cap nhat gio hang");
			System.out.println("\t3. Danh sach tat ca cac gio hang");
			System.out.println("\t4. Danh sach cac gio hang da thanh toan");
			System.out.println("\t5. Thong ke tong so tien thu duoc theo khach hang");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				createCart();
				break;
			case 2:
				updateCart();
				break;
			case 3:
				displayAll();
			case 4:
				displayPayed();
				break;
			case 5:
				count();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		} while (true);
	}

	private static void createCart() {
		System.out.println("---------------Tao gio hang cho khach den shop----------------------");
		System.out.print("Nhap id khach hang: ");
		int idCustomer = Integer.parseInt(sc.nextLine());

		ArrayList<Integer> _listProduct = new ArrayList<Integer>();

		listCarts.add(new Cart(autoId++, idCustomer, _listProduct));

		System.out.println("Tao gio hang thanh cong");
	}

	private static void updateCart() {
		System.out.println("----------------Cap nhat gio hang------------------");
		System.out.print("Nhap id gio hang muon cap nhat: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findCartById(id);
		if (index == -1) {
			System.out.println("Gio hang chua duoc khoi tao");
			return;
		}
		listCarts.get(index).update();
	}


	private static void displayAll() {
		System.out.println("\nDanh sach tat ca gio hang");
		double total = 0;
		for (Cart x : listCarts) {
			x.display();
			total += x.tongTien();
		}
		if(total == 0) {
			System.out.println("Khong co gio hang nao");
			return;
		}
	}
	
	private static void displayPayed() {
		System.out.println("\nDanh sach cac gio hang da thanh toan");
		double total = 0;
		for (Cart x : payedCarts) {
			x.display();
			total += x.tongTien();
		}
		if(total == 0) {
			System.out.println("Khong co gio hang nao");
			return;
		}
		System.out.printf("\t\tTong tien thu duoc tu tat ca cac gio hang: %10.2f%n", total);
	}

	private static void count() {
		System.out.println("------------Thong ke tong so tien thu duoc theo khach hang----------------");
		for (Customer x : CustomerManagement.getList()) {
			double total = 0;
			int _customerId = x.getId();
			System.out.println("ID khach hang: " + _customerId);
			for (Cart cart : payedCarts) {
				if (cart.getCustomerID() == _customerId) {
					total += cart.tongTien();
				}
			}
			System.out.printf("Tong tien thu duoc tu khach hang co ID %d: %10.2f%n", _customerId, total);
		}

	}

	public static int findCartById(int id) {
		for (int i = 0; i < listCarts.size(); i++) {
			if (listCarts.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
