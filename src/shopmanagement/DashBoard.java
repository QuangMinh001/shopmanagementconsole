package shopmanagement;

import java.util.Scanner;

import shopmanagement.repository.search.SearchManagement;
import shopmanagement.repository.sell.CartManagement;
import shopmanagement.repository.update.UpdateManagement;

public class DashBoard {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		do {
			System.out.println("\n\n=========QUAN LY SHOP=============");
			System.out.println("Chon chuc nang quan ly: ");
			System.out.println("\t1. Cap nhat thong tin he thong");
			System.out.println("\t2. Quan ly ban hang");
			System.out.println("\t3. Tim kiem");
			System.out.println("\t0. Thoat");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				UpdateManagement.execute();
				break;
			case 2:
				CartManagement.execute();
				break;
			case 3:
				SearchManagement.execute();
				break;
			case 0:
				System.out.println("Thoat chuong trinh");
				System.exit(0);
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}
		} while (true);
	}
}
