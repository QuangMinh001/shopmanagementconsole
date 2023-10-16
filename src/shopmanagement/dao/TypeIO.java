package shopmanagement.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import shopmanagement.EFilePath;
import shopmanagement.modul.Type;

public class TypeIO extends DataIO<Type> implements EFilePath{
	public static ArrayList<Type> loadData() {
		String path = productFilePath;
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

}
