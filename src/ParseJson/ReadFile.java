package ParseJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
	public String ReadFile(String path) {
		BufferedReader reader = null;
		String laststr = "";
		File file = new File(path);
		try {
			if (!file.isDirectory()) {
				FileInputStream fileInputStream = new FileInputStream(path);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
				reader = new BufferedReader(inputStreamReader);
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					laststr+=tempString;
				}
				reader.close();
			}else{
				String[] paths=file.list();
				for(String eachPath:paths){
					FileInputStream fileInputStream = new FileInputStream(eachPath);
					InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
					reader = new BufferedReader(inputStreamReader);
					String tempString = null;
					while ((tempString = reader.readLine()) != null) {
						laststr+=tempString;
					}
					reader.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr.toString();
	}
}
