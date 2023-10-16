package shopmanagement.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import shopmanagement.modul.BaseModel;


public abstract class DataIO<E extends BaseModel> {

	public void saveData(ArrayList<E> list, String path) {
		File file = new File(path);
		FileWriter fwriter = null;
		BufferedWriter bufW = null;

		try {
			fwriter = new FileWriter(file);
			bufW = new BufferedWriter(fwriter);
			for (E obj : list) {
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
