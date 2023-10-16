package shopmanagement.modul;

import java.util.Scanner;

public class Type implements BaseModel {
	private int id;
	private String name;
	private String description;

	@Override
	public void display() {
		System.out.printf("%4d %-30s %-30s%n", this.id, this.name, this.description);
	}

	public static Scanner sc = new Scanner(System.in);

	@Override
	public void update() {
		do {
			System.out.println("\n\n\tChon chuc nang cap nhat: ");
			System.out.println("\t1. Cap nhat ten");
			System.out.println("\t2. Cap nhat mo ta");
			System.out.println("\t0. Quay lai");
			System.out.print("Lua chon cua ban: ");
			int chon = Integer.parseInt(sc.nextLine());
			switch (chon) {
			case 1:
				updateName();
				break;
			case 2:
				updateDescription();
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
		System.out.println("\n------------Cap nhat ten chung loai-----------");
		System.out.println("Ten cu: " + this.getName());
		System.out.print("Nhap ten moi: ");
		String _name = sc.nextLine();
		if (_name.trim().length() == 0) {
			System.out.println("Ten khong duoc de trong");
			return;
		}
		this.setName(_name);
		System.out.println("Cap nhat ten thanh cong");
	}

	private void updateDescription() {
		System.out.println("\n---------------Cap nhat mo ta---------------");
		System.out.println("Mo ta cu: " + this.getDescription());
		System.out.print("Nhap mo ta moi: ");
		String _description = sc.nextLine();
		if (_description.trim().length() == 0) {
			System.out.println("Mo ta khong duoc de trong");
			return;
		}
		this.setDescription(_description);
		System.out.println("Cap nhat mo ta thanh cong");
	}

	public Type() {
		super();
	}

	public Type(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Type(String line) {
		String parts[] = line.split(";");
		this.id = Integer.parseInt(parts[0]);
		this.name = parts[1];
		this.description = parts[2];
	}
	
	@Override
	public String toString() {
		String out = String.format("%d;%s;%s", this.id, this.name, this.description);  
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
