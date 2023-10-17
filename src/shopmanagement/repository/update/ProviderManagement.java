package shopmanagement.repository.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import shopmanagement.EFilePath;
import shopmanagement.dao.ProviderIO;
import shopmanagement.modul.Provider;

public class ProviderManagement implements EFilePath {
	
	private static ArrayList<Provider> list = ProviderIO.loadData();

	private static int setAutoId(){
		int autoId;
		if(list.size() >= 1 ) {
			autoId = list.get(list.size()-1).getId() + 1;
		}else {
			autoId = 1;
		}
		return autoId;
	}
	
	public static ArrayList<Provider> getList() {
		return list;
	}

	public static void setList(ArrayList<Provider> list) {
		ProviderManagement.list = list;
	}

	public static Scanner sc = new Scanner(System.in);

	public static void providerUpdate() {
		do {
			System.out.println("\n\n-----------Cap nhat danh sach nha cung cap------------");
			System.out.println("Chon chuc nang quan ly: ");
			System.out.println("\t1. Hien thi danh sach ");
			System.out.println("\t2. Them nha cung cap");
			System.out.println("\t3. Sua nha cung cap");
			System.out.println("\t4. Xoa nha cung cap");
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

	private static void sort(ArrayList<Provider> listClone) {
		Collections.sort(listClone, new Comparator<Provider>() {
			@Override
			public int compare(Provider o1, Provider o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}

		});
	}

	private static void display() {
		@SuppressWarnings("unchecked")
		ArrayList<Provider> sortList = (ArrayList<Provider>) list.clone();
		sort(sortList);
		System.out.println("\n----------------Danh sach cac nha cung cap-------------");
		System.out.printf("%4s %-30s %-30s%n", "ID", "Ten nha cung cap", "Dia chi");
		for (Provider x : sortList) {
			x.display();
		}
		ProviderIO.saveData(list);
	}

	private static void add() {
		System.out.println("\n---------------Them nha cung cap-------------------");
		System.out.print("Nhap ten nha cung cap: ");
		String name = sc.nextLine();
		if (name.trim().length() == 0) {
			System.out.println("Ten nha cung cap khong duoc de trong");
			return;
		}
		System.out.print("Nhap dia chi: ");
		String address = sc.nextLine();
		if (address.trim().length() == 0) {
			System.out.println("Dia chi khong duoc de trong");
			return;
		}
		list.add(new Provider(setAutoId(), name, address));
		System.out.println("Them nha cung cap thanh cong...!");
		ProviderIO.saveData(list);
	}

	private static void update() {
		System.out.println("\n-------------Cap nhat thong tin nha cung cap-----------");
		System.out.print("Nhap id nha cung cap: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findProviderById(id);
		if (index == -1) {
			System.out.println("Nha cung cap khong co trong danh sach");
			return;
		}
		list.get(index).update();
		ProviderIO.saveData(list);
	}

	private static void remove() {
		System.out.println("\n-------------Xoa nha cung cap-------------");
		System.out.print("Nhap id nha cung cap: ");
		int id = Integer.parseInt(sc.nextLine());
		int index = findProviderById(id);
		if (index == -1) {
			System.out.println("Nha cung cap khong co trong danh sach");
			return;
		}
		list.remove(index);
		System.out.println("Xoa nha cung cap thanh cong...!");
		ProviderIO.saveData(list);
	}

	// Tim nha cung cap theo id
	public static int findProviderById(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	public static Provider getProviderById(int id) {
		for (Provider x : list) {
			if (x.getId() == id) {
				return x;
			}
		}
		return null;
	}
}
