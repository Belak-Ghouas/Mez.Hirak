	package Building;


	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	

public class WriteStorage{

	//
		private PrintWriter writer;
		
		public WriteStorage(String path) {

			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(path, true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Set true for append mode
			writer = new PrintWriter(fileWriter);

		}

		public void writeElement( String content) {
			String debut = content.trim();

			writer.print(debut);
			

		}
		public void WriteStart(String attribut) {
			String debut = "<"+attribut+">"+"\n";
			writer.print(debut);
			
		}
		public void WriteFinish(String attribut) {
			String fin = "</"+attribut+"> \n";
			writer.print(fin);
			
		}

		public void WritedebutDocument() {
			String debut = "<root>\n";
			writer.print(debut);
			
		}

		public void WritefinDocument() {
			String debut = "</root>";
			writer.print(debut);
			writer.close();
		}
		//

		
	

}
