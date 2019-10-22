package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class FetchContent {
	private HashMap<String, Integer> stats = new HashMap<String, Integer>();
	
	public FetchContent(URL url) {

		try {
			if (url == null)
				url = new URL("http://pastebin.com/raw/s4yE08Ek");
			
			URLConnection conn = url.openConnection();

			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				String[] arr = inputLine.split(" ");
				
				for(int i = 0; i < arr.length; i++) {
					String initial = arr[i].split("")[0].toLowerCase();
					
					int value = 1;
					
					String vogais = "a e i o u";


					if (!vogais.contains(initial)) initial = "consoantes";
					
					if (stats.containsKey(initial)) {
						value = stats.get(initial) + 1;
					}
					
					stats.put(initial, value);
				}
			}
			
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void createFile(String fileName){
		if (fileName == null) fileName = "estatistica.txt";
		
		try {

			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			stats.forEach((String t, Integer a) -> {
				out.println(t + " = " + a);
			});
			
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printFile(String fileName) {
		if (fileName == null) fileName = "estatistica.txt";
        String line = null;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                System.out.println(line);
            }   

            br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Erro ao abrir o arquivo '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Erro ao ler o arquivo '" + fileName + "'");
        }
	}
}
