package shopmanagement.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import shopmanagement.EFilePath;
import shopmanagement.modul.Type;

public class TypeIO implements EFilePath{
	public static ArrayList<Type> loadData() {
		String path = typeFilePath;
		File file = new File(path);
		FileReader fread = null;
		BufferedReader bufR = null;

		ArrayList<Type> list = new ArrayList<Type>();

		try {
			fread = new FileReader(file);
			bufR = new BufferedReader(fread);
			String line;
			while ((line = bufR.readLine()) != null) {
				Type b = new Type(line);
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
	
	public static void saveData(ArrayList<Type> list) {
		String path = typeFilePath;
		File file = new File(path);
		FileWriter fwriter = null;
		BufferedWriter bufW = null;

		try {
			fwriter = new FileWriter(file);
			bufW = new BufferedWriter(fwriter);
			for (Type obj : list) {
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
