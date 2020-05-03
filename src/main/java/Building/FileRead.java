package Building;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class FileRead {

	public static HashSet<String> readFile(String path) {

		HashSet<String> result = new HashSet<String>();
		File file = new File(path);
		FileReader fr;
		BufferedReader br;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				result.add((line.trim().toLowerCase()));
			}

			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	public static List<String> readFileList(String path) {

		List<String> result = new ArrayList<String>();
		File file = new File(path);
		FileReader fr;
		BufferedReader br;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				
				result.add((line.trim().toLowerCase()));
				
			}
			result.sort(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if( o1.length()<o2.length()) {
						return 1;
					}else {
						 if(o1.length()>o2.length()){
								return -1 ;
							}else return 0 ;
					}
						
					
				}
			});;
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
