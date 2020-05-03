package Building;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Traitement.Page;

public class WriteXmlFile {
//
	private PrintWriter writer;
	private static WriteXmlFile Singleton;

	private WriteXmlFile() throws IOException {

		FileWriter fileWriter = new FileWriter("src/main/resources/storage/corpus.xml", true); // Set true for append mode
		writer = new PrintWriter(fileWriter);

	}

	public void createElement(Page page) {
		String debut = "<page > \n" + "<id> " + page.getId() + " </id>\n" + "<title> " + page.getTitle()
				+ " </title> \n" + "<text> " + page.getText() + " </text> \n" + "</page> \n";

		writer.print(debut);
		// writer.close();

	}

	public void WritedebutDocument() {
		String debut = "<root>\n";
		writer.print(debut);
		// writer.close();
	}

	public void WritefinDocument() {
		String debut = "</root>";
		writer.print(debut);
		writer.close();
	}
	//

	public static synchronized WriteXmlFile getInsance() {
		// TODO Auto-generated method stub
		if (Singleton != null)
			return Singleton;

		try {
			Singleton = new WriteXmlFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Singleton;
	}
}
