package com.shinsegae.android.ssgnoti;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class DataSource {
	Context context;
	private String selectedDept;

	public DataSource(Context context) {
		this.context = context;
	}

	public void save(String selectedDept) {
		this.selectedDept = selectedDept;
		// 파일로 저장
		saveCodeToFile(selectedDept);

	}

	private void saveCodeToFile(String selectedDept2) {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(context.openFileOutput("file_dept",
					Context.MODE_PRIVATE));
			bos.write(selectedDept2.getBytes());
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		} finally {
			try {
				if (bos != null) {
					bos.flush();
					bos.close();
				}
			} catch (Exception ex) {
			}
			;
		}

	}

	public String getSelectedDept() {
		try {
			FileInputStream fis = context.openFileInput("file_dept");
			this.selectedDept = loadCodeFromFile(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return selectedDept;
	}

	private static String loadCodeFromFile(InputStream fres) {
		BufferedInputStream bis = null;
		StringBuffer sb = new StringBuffer();
		try {
			bis = new BufferedInputStream(fres);
			byte[] buf = new byte[1024];
			int numRead = 0;
			while ((numRead = bis.read(buf)) != -1) {
				String readData = new String(buf, 0, numRead);
				sb.append(readData);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (bis != null)
					bis.close();
			} catch (Exception e) {
			}
			;
		}
		return sb.toString();
	}

}
