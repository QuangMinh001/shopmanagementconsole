package shopmanagement.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import shopmanagement.EFilePath;
import shopmanagement.modul.Customer;

public class CustomerIO implements EFilePath{
	
	public static ArrayList<Customer> loadData() {
		String path = customerFilePath;
		File file = new File(path);
		FileReader fread = null;
		BufferedReader bufR = null;

		ArrayList<Customer> list = new ArrayList<Customer>();

		try {
			fread = new FileReader(file);
			bufR = new BufferedReader(fread);
			String line;
			while ((line = bufR.readLine()) != null) {
				Customer b = new Customer(line);
				list.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufR != null)
					bufR.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
	
	public static void saveData(ArrayList<Customer> list) {
		String path = customerFilePath;
		File file = new File(path);
		FileWriter fwriter = null;
		BufferedWriter bufW = null;

		try {
			fwriter = new FileWriter(file);
			bufW = new BufferedWriter(fwriter);
			for (Customer obj : list) {
				bufW.write(obj.toString());
				bufW.newLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufW != null)
					bufW.close();
			} catch (Exception e) {
			}
		}
	}

}
