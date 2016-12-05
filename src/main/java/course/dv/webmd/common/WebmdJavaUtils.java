package course.dv.webmd.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebmdJavaUtils {

	private static Set<String> stopWords;
	
	/**
	 * This method sorts Map<String, Integer> by value in descending order.
	 * @param unsortMap
	 * @return Map<String, Integer>
	 */
	public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
		List<Map.Entry<String, Integer>> list =
				new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	/**
	 * This method populates the class variable stopWords (a HashSet). This
	 * HashSet is used elsewhere.
	 *  
	 * @param filepath
	 * @throws IOException
	 */
	public static void populateStopWords(String filepath) throws IOException
	{
		if(stopWords == null) {
			stopWords = new HashSet<String>();
			try {
				File file = null;
				FileReader fr = null;
				String currentLine = "";
				file = new File(filepath, "stopwordslist.txt");
				fr = new FileReader(file.getAbsoluteFile());

				BufferedReader br= new BufferedReader(fr);
				while ((currentLine = br.readLine()) != null){
					stopWords.add(currentLine);
				}
				br.close();
				fr.close();
			}
			catch(Exception e) {
				System.out.println("Exception in Stop Words - WebmdJavaUtils");
			}
		}
	}

	/**
	 * This method removes all 'StopWords' from the String
	 * content passed as parameter.
	 * NOTE: Make sure to call getStopWords(String filePath) before calling this method.
	 * 
	 * @param String
	 * @return String
	 */
	public static String removeStopWords(String string)
	{
		if(stopWords != null) {
			for(String stopWord : stopWords){
				if(stopWord.equals("")) continue;
				Pattern p = Pattern.compile("\\b"+stopWord+"\\b", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(string);
				while (m.find()) {
					string = m.replaceAll("");
				}
			}
		}
		string = string.trim().replaceAll(" +", " ");
		return string;
	}
}
