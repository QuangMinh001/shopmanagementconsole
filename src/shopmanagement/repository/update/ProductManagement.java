package shopmanagement.repository.update;

import java.util.ArrayList;
import java.util.Scanner;

import shopmanagement.EFilePath;
import shopmanagement.dao.ProductIO;
import shopmanagement.modul.Product;

public class ProductManagement implements EFilePath{
	
	static ProductIO saveObj = new ProductIO();
	private static ArrayList<Product> list = ProductIO.loadData();
	
	private static int setAutoId(){
		int autoId;
		if(list.size() >= 1 ) {
			autoId = list.get(list.size()-1).getId() + 1;
		}else {
			autoId = 1;
		}
		return autoId;
	}

	public static ArrayList<Product> getList() {
		return list;
	}

	public static void setList(ArrayList<Product> list) {
		ProductManagement.list = list;
	}

	public static Scanner sc = new Scanner(System.in);

	public static void productUpdate() {
		do {
			System.out.println("\n\n----------Cap nhat danh sach san pham--------------");
			System.out.println("Chon chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach");
			System.out.println("\t2. Them san pham");
			System.out.println("\t3. Sua san pham");
			System.out.println("\t4. Xoa san pham");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				display();
				break;
			case 2:
				add();
				break;
			case 3:
				update();
				break;
			case 4:
				remove();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		} while (true);
	}

	private static void display() {
		System.out.println("\n----------------Danh sach san pham--------------------");
		System.out.printf("%-4s %-25s %-20s %-30s %-8s %-9s%n", "ID", "Ten nha cung cap", "Ten loai", "Ten san pham",
				"So luong", "Don gia");
		for (Product x : list) {
			x.display();
		}
		saveObj.saveData(list, productFilePath);
	}

	private static void add() {
		System.out.println("\n------------Them san pham----------------------");
		System.out.print("Nhap id nha cung cap: ");
		int providerID = Integer.parseInt(sc.nextLine());
		int indexProviderID = ProviderManagement.findProviderById(providerID);
		if (indexProviderID == -1) {
			System.out.println("Nha cung cap khong co trong danh sach");
			return;
		}
		System.out.print("Nhap id chung loai: ");
		int typeID = Integer.parseInt(sc.nextLine());
		int indexTypeID = TypeManagement.findTypeById(typeID);
		if (indexTypeID == -1) {
			System.out.println("Chung loai khong co trong danh sach");
			return;
		}
		int check = isProductExisted(providerID, typeID);
		if (check != -1) {
			System.out.println("San pham da co trong danh sach");
			return;
		}
		System.out.print("Nhap ten san pham: ");
		String name = sc.nextLine();
		if (name.trim().length() == 0) {
			System.out.println("Ten san pham khong duoc de trong");
			return;
		}
		System.out.print("Nhap so luong: ");
		int amount = Integer.parseInt(sc.nextLine());
		System.out.print("Nhap don gia: ");
		double price = Double.parseDouble(sc.nextLine());

		list.add(new Product(setAutoId(), providerID, typeID, name, amount, price));
		System.out.println("Them san pham thanh cong");
		saveObj.saveData(list, productFilePath);
	}

	private static void update() {
		System.out.println("\n--------------Cap nhat thong tin san pham----------------------");
		System.out.print("Nhap id san pham: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findProductById(id);
		if (index == -1) {
			System.out.println("San pham khong co trong danh sach");
			return;
		}
		list.get(index).update();
		saveObj.saveData(list, productFilePath);
	}

	private static void remove() {
		System.out.println("\n---------------Xoa san pham-----------------");
		System.out.print("Nhap id san pham: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findProductById(id);
		if (index == -1) {
			System.out.println("San pham khong co trong danh sach");
			return;
		}
		list.remove(index);
		System.out.println("Xoa san pham thanh cong..!");
		saveObj.saveData(list, productFilePath);
	}

	public static Product getProductById(int id) {
		for (Product x : list) {
			if (x.getId() == id) {
				return x;
			}
		}
		return null;
	}

	public static int findProductById(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public static int isProductExisted(int providerID, int typeID) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProviderID() == providerID && list.get(i).getTypeID() == typeID) {
				return i;
			}
		}
		return -1;
	}
}
