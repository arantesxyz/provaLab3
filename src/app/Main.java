package app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static FetchContent content;

	public static void main(String[] args) {
		menu();

	}
	
	private static void menu() {
		 int choice = 0;
	        Scanner scan = new Scanner(System.in);
	        
	        do {
	        	System.out.println("");
	            System.out.println("-------------------------\n");
	            System.out.println("1 - Informar URL");
	            System.out.println("2 - Gerar Estatísticas");
	            System.out.println("3 - Mostrar Estatísticas");
	            System.out.println("4 - Sair");

	        	
	            choice = scan.nextInt();
	            
	            switch (choice) {
	            case 1: 
	            	url();
	            	break;
	            
	            case 2: 
	            	write();
	            	break;
	            
	            case 3: 
	            	read();
            		break;
	            
	            case 4: break;
	            default: System.out.println("Opção inválida");
	            }
	        } while (choice != 4);
	        scan.close();
	}
	
	private static void url() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Sugestão de URL: http://pastebin.com/raw/s4yE08Ek");
    	System.out.print("Informe a URL:");
    	String url = scan.nextLine();
    	
    	try {
    		content = new FetchContent(new URL(url));
    		
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
    	
    	//scan.close();
    	return;
	}
	
	private static void write() {
		if (content == null) {System.out.println("Informe a url primeiro."); return;}
		content.createFile(null);
	}
	
	private static void read() {
		if (content == null) {System.out.println("Informe a url primeiro."); return;}
		content.printFile(null);
	}

}
