package shopmanagement.repository.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import shopmanagement.EFilePath;
import shopmanagement.dao.CustomerIO;
import shopmanagement.modul.Customer;

public class CustomerManagement implements EFilePath{

	private static ArrayList<Customer> list = CustomerIO.loadData();
	
	private static int setAutoId(){
		int autoId;
		if(list.size() >= 1 ) {
			autoId = list.get(list.size()-1).getId() + 1;
		}else {
			autoId = 1;
		}
		return autoId;
	}
	
	public static ArrayList<Customer> getList() {
		return list;
	}

	public static void setList(ArrayList<Customer> list) {
		CustomerManagement.list = list;
	}

	public static Scanner sc = new Scanner(System.in);


	public static void customerUpdate() {
		do {
			System.out.println("\n\n---------------Cap nhat danh sach khach hang---------------");
			System.out.println("Chon chuc nang quan ly");
			System.out.println("\t1. Hien thi danh sach");
			System.out.println("\t2. Them khach hang");
			System.out.println("\t3. Sua thong tin khach hang");
			System.out.println("\t4. Xoa khach hang");
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

	private static void sort(ArrayList<Customer> listClone) {
		Collections.sort(listClone, new Comparator<Customer>() {
			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}

	
	private static void display() {
		@SuppressWarnings("unchecked")
		ArrayList<Customer> sortList = (ArrayList<Customer>) list.clone();
		sort(sortList);
		System.out.println("\n--------------Danh sach khach hang--------------------");
		System.out.printf("%-4s %-30s %-12s %-20s%n", "ID", "Ten khach hang", "Dien thoai", "Dia chi");
		for (Customer x : sortList) {
			x.display();
		}
		CustomerIO.saveData(list);
	}

	private static void add() {
		System.out.println("\n--------------Them khach hang-------------------");
		System.out.print("Nhap ten khach hang: ");
		String _name = sc.nextLine();
		if (_name.trim().length() == 0) {
			System.out.println("Ten khach hang khong duoc de trong");
			return;
		}
		System.out.print("Nhap dien thoai: ");
		String _phone = sc.nextLine();
		if (_phone.trim().length() == 0) {
			System.out.println("Dien thoai khong duoc de trong");
			return;
		}
		System.out.print("Nhap dia chi: ");
		String _address = sc.nextLine();
		if (_address.trim().length() == 0) {
			System.out.println("Dia chi khong duoc de trong");
			return;
		}
		list.add(new Customer(setAutoId(), _name, _phone, _address));
		System.out.println("Them khach hang thanh cong..!");
		CustomerIO.saveData(list);
	}

	private static void update() {
		System.out.println("\n------------Cap nhat thong tin khach hang-------------");
		System.out.print("Nhap id khach hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findCustomerById(id);
		if (index == -1) {
			System.out.println("Khach hang khong co trong danh sach");
			return;
		}
		list.get(index).update();
		CustomerIO.saveData(list);
	}

	private static void remove() {
		System.out.println("\n------------Xoa khach hang----------------");
		System.out.print("Nhap id khach hang: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findCustomerById(id);
		if (index == -1) {
			System.out.println("Khach hang khong co trong danh sach");
			return;
		}
		list.remove(index);
		System.out.println("Xoa khach hang thanh cong");
		CustomerIO.saveData(list);
	}

	public static Customer getCustomerById(int id) {
		for(Customer c: list) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public static int findCustomerById(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
