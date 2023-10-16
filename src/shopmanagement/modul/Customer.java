package shopmanagement.modul;

import java.util.Scanner;

public class Customer implements BaseModel{
	private int id;
	private String name;
	private String phone;
	private String address;

	@Override
	public void display() {
		System.out.printf("%4d %-30s %12s %-20s%n", this.id, this.name, this.phone, this.address);
	}

	public static Scanner sc = new Scanner(System.in);

	@Override
	public void update() {
		do {
			System.out.println("\n\n\tChon chuc nang cap nhat");
			System.out.println("\t1. Cap nhat ten");
			System.out.println("\t2. Cap nhat dien thoai");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				updateName();
				break;
			case 2:
				updatePhone();
				break;
			case 3:
				updateAddress();
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
		System.out.println("\n-------------Cap nhat ten khach hang------------");
		System.out.println("Ten cu: " + this.getName());
		System.out.print("Nhap ten khach hang moi: ");
		String _name = sc.nextLine();
		if (_name.trim().length() == 0) {
			System.out.println("Ten khach hang khong duoc de trong");
			return;
		}
		this.setName(_name);
		System.out.println("Cap nhat ten thanh cong...!");

	}

	private void updatePhone() {
		System.out.println("\n-------------Cap nhat so dien thoai------------");
		System.out.println("So dien thoai cu: " + this.getPhone());
		System.out.print("Nhap so dien thoai moi: ");
		String _phone = sc.nextLine();
		if (_phone.trim().length() == 0) {
			System.out.println("So dien thoai khong duoc de trong");
			return;
		}
		this.setPhone(_phone);
		System.out.println("Cap nhat so dien thoai thanh cong...!");

	}
	
	private void updateAddress() {
		System.out.println("\n-------------Cap nhat dia chi-----------");
		System.out.println("Dia chi cu: " + this.getAddress());
		System.out.print("Nhap dia chi moi: ");
		String _address = sc.nextLine();
		if (_address.trim().length() == 0) {
			System.out.println("Dia chi khong duoc de trong");
			return;
		}
		this.setPhone(_address);
		System.out.println("Cap nhat dia chi thanh cong...!");

	}

	public Customer() {
		super();
	}

	public Customer(int id, String name, String phone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	
	public Customer(String line) {
		String parts[] = line.split(";");
		this.id = Integer.parseInt(parts[0]);
		this.name = parts[1];
		this.phone = parts[2];
		this.address = parts[3];
	}
	
	@Override
	public String toString() {
		String out = String.format("%d;%s;%s;%s", this.id, this.name, this.phone, this.address);  
		return out;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


}
