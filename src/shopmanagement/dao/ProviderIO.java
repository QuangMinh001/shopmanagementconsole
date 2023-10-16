package shopmanagement.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import shopmanagement.EFilePath;
import shopmanagement.modul.Provider;

public class ProviderIO extends DataIO<Provider> implements EFilePath {
	public static ArrayList<Provider> loadData() {
		String path = providerFilePath;
		File file = new File(path);
		FileReader fread = null;
		BufferedReader bufR = null;

		ArrayList<Provider> list = new ArrayList<Provider>();

		try {
			fread = new FileReader(file);
			bufR = new BufferedReader(fread);
			String line;
			while ((line = bufR.readLine()) != null) {
				Provider b = new Provider(line);
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
