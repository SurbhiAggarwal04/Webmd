package course.dv.webmd.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateCSVFile {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static  String FILE_HEADER;

	public static void getCsvForTopicPopularityFromHashMap(Map<String, Long> map, String filepath,String filename) {
		FILE_HEADER = "id,value";
		File file = new File(filepath, filename);
		FileWriter writer = null;
		//if(!file.exists())
		//{
		try {
			writer = new FileWriter(file.getAbsoluteFile());
			writer.append(FILE_HEADER.toString());
			writer.append(NEW_LINE_SEPARATOR);

			for (Entry<String, Long> entry : map.entrySet()) {
				writer.append(entry.getKey());
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(entry.getValue()));
				writer.append(NEW_LINE_SEPARATOR);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//}
	}
	public static void getCsvFromHashMap(Map<String, Long> map, String filepath,String filename) {
		FILE_HEADER = "id,name,value";
		File file = new File(filepath, filename);
		FileWriter writer = null;
		//if(!file.exists())
		//{
		try {
			writer = new FileWriter(file.getAbsoluteFile());
			writer.append(FILE_HEADER.toString());
			writer.append(NEW_LINE_SEPARATOR);

			for (Entry<String, Long> entry : map.entrySet()) {
				String topicIdName=entry.getKey();
				String[] idName=topicIdName.split("-");
				writer.append(idName[0]);
				writer.append(COMMA_DELIMITER);
				writer.append(idName[1]);
				writer.append(COMMA_DELIMITER);
				writer.append(String.valueOf(entry.getValue()));
				writer.append(NEW_LINE_SEPARATOR);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//}
	}

}
