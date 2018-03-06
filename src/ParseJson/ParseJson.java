package ParseJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJson {
	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\json");
		String[] paths = file.list();
		String abstractpath = file.getAbsolutePath();
		File fileOut = new File("D:\\output.txt");
		if (!fileOut.exists())
			fileOut.createNewFile();

		for (String fileName : paths) {
			String fullPath = abstractpath + "\\" + fileName;
			String JsonContext = new ReadFile().ReadFile(fullPath);

			JSONArray jsonArray = JSONArray.fromObject(JsonContext);
			System.out.println(jsonArray);
			int index = jsonArray.size();
			for (int i = 0; i < index; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String name = (String) jsonObject.get("name");
				String unit = "";
				if (!jsonObject.get("unit").equals("null"))
					unit = (String) jsonObject.get("unit");
				String type = jsonObject.get("type") != null ? (String) jsonObject.get("type") : "";

				String valueMin = jsonObject.get("valueMin") != null ? jsonObject.get("valueMin").toString() : "";
				String valueMax = jsonObject.get("valueMax") != null ? jsonObject.get("valueMax").toString() : "";
				String cn = jsonObject.get("cn") != null ? (String) jsonObject.get("cn") : "";
				String cdescr = jsonObject.get("cdescr") != null ? (String) jsonObject.get("cdescr") : "";
				//String integration = jsonObject.get("integration") != null ? (String) jsonObject.get("integration") : "";
				int accuracy = jsonObject.get("integration") != null ? (int) jsonObject.get("accuracy") : 0;
				StringBuilder sb1 = new StringBuilder();
				sb1.append("insert into metric_meta_data(name, unit, value_min, value_max, accuracy, data_type,cn,cdescr) values(");
				sb1.append("'" + name + "',");
				sb1.append("'" + unit + "',");
				sb1.append(valueMin.toString() + ",");
				sb1.append(valueMax.toString() + ",");
				sb1.append(accuracy + ",");
				sb1.append("'" + type + "'" + ",");
				sb1.append("'" + cn + "'" + ",");
				sb1.append("'" + cdescr + "');");
				// System.out.println(sb1);

				StringBuilder sb2 = new StringBuilder();
				sb2.append("update metric_meta_data set cn=" + "'" + cn + "' , cdescr='" + cdescr + "' where name='" + name
						+ "';");

//				StringBuilder sb3 = new StringBuilder();
//				sb2.append("update metric_meta_data set  integration='" + integration + "' where name='" + name + "';");

				FileWriter fw = new FileWriter("D:\\output.txt", true);
				PrintWriter pw = new PrintWriter(fw);
				// insert
				 pw.print(sb1 + "\n");
				// update
				// pw.print(sb2 + "\n");
				//pw.print(sb3 + "\n");
				pw.flush();
				/*
				 * Entity entity= new Entity(); entity.setName();
				 */
			}
		}
	}
}
