package shopmanagement.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import shopmanagement.EFilePath;
import shopmanagement.modul.Product;

public class ProductIO implements EFilePath{
	
	public static ArrayList<Product> loadData() {
		String path = productFilePath;
		File file = new File(path);
		FileReader fread = null;
		BufferedReader bufR = null;

		ArrayList<Product> list = new ArrayList<Product>();

		try {
			fread = new FileReader(file);
			bufR = new BufferedReader(fread);
			String line;
			while ((line = bufR.readLine()) != null) {
				Product b = new Product(line);
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
	
	public static void saveData(ArrayList<Product> list) {
		String path = productFilePath;
		File file = new File(path);
		FileWriter fwriter = null;
		BufferedWriter bufW = null;

		try {
			fwriter = new FileWriter(file);
			bufW = new BufferedWriter(fwriter);
			for (Product obj : list) {
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
