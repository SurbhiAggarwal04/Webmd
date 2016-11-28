package course.dv.webmd.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class GenerateCSVFile {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,value";

	public static void getCsvFromHashMap(Map<String, Long> map, String filepath,String filename) {

		File file = new File(filepath, filename);
		FileWriter writer = null;
		if(!file.exists())
		{
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
	}
	}

}
