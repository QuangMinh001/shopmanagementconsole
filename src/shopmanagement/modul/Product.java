package shopmanagement.modul;

import java.util.Scanner;

import shopmanagement.repository.update.ProviderManagement;
import shopmanagement.repository.update.TypeManagement;

public class Product implements BaseModel {
	private int id;
	private int providerId;
	private int typeId;
	private String name;
	private int amount;
	private double price;

	@Override
	public void display() {
		Provider provider = ProviderManagement.getProviderById(this.providerId);
		Type type = TypeManagement.getTypeById(this.typeId);
		if (provider == null || type == null) {
			System.out.println("Co loi du lieu");
			return;
		}
		System.out.printf("%4d %-25s %-20s %-30s %8d %9.2f%n", this.id, provider.getName(), type.getName(),
				this.getName(), this.amount, this.price);
	}

	public static Scanner sc = new Scanner(System.in);

	@Override
	public void update() {
		do {
			System.out.println("\n\n\tChon chuc nang cap nhat: ");
			System.out.println("\t1. Sua ten san pham");
			System.out.println("\t2. Sua so luong");
			System.out.println("\t3. Sua don gia");
			System.out.println("\t0. Thoat");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				updateName();
				break;
			case 2:
				updateAmount();
				break;
			case 3:
				updatePrice();
				break;
			case 0:
				return;
			default:
				System.out.println("Lua chon khong hop le");
				break;
			}

		} while (true);
	}

	private void updateName() {
		System.out.println("\n----------Cap nhat ten san pham----------------");
		System.out.println("Ten cu: " + this.getName());
		System.out.print("Nhap ten moi: ");
		String name = sc.nextLine();
		if (name.trim().length() == 0) {
			System.out.println("Ten san pham khong duoc de trong");
			return;
		}
		this.setName(name);
		System.out.println("Cap nhat ten san pham thanh cong");
	}

	private void updateAmount() {
		System.out.println("\n----------Cap nhat so luong san pham-------------");
		System.out.println("So luong cu: " + this.getAmount());
		System.out.print("Nhap so luong moi: ");
		int amount = Integer.parseInt(sc.nextLine());
		this.setAmount(amount);
		System.out.println("Cap nhat so luong thanh cong..!");
	}

	private void updatePrice() {
		System.out.println("\n-----------Cap nhat don gia san pham--------------");
		System.out.printf("Don gia cu: %8.2f%n", this.getPrice());
		System.out.print("Nhap don gia moi: ");
		double price = Double.parseDouble(sc.nextLine());
		this.setPrice(price);
		System.out.println("Cap nhat don gia thanh cong..!");
	}

	public Product() {
		super();
	}

	public Product(int id, int providerID, int typeID, String name, int amount, double price) {
		super();
		this.id = id;
		this.providerId = providerID;
		this.typeId = typeID;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}
	
	public Product(String line) {
		String parts[] = line.split(";");
		this.id = Integer.parseInt(parts[0]);
		this.providerId = Integer.parseInt(parts[1]);
		this.typeId = Integer.parseInt(parts[2]);
		this.name = parts[3];
		this.amount = Integer.parseInt(parts[4]);
		this.price = Double.parseDouble(parts[5]);
	}
	
	@Override
	public String toString() {
		return String.format("%d;%d;%d;%s;%d;%f", id, providerId, typeId, name, amount, price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProviderID() {
		return providerId;
	}

	public void setProviderID(int providerID) {
		this.providerId = providerID;
	}

	public int getTypeID() {
		return typeId;
	}

	public void setTypeID(int typeID) {
		this.typeId = typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
