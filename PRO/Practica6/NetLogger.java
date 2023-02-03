package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class NetLogger {
	
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			createStructure();
		} catch (IOException e) {
			writeErrorInErrLog(e.getMessage());
		}
		reorganiseNetFolder();
		//---------------------------------------------
		String op="";
		boolean stay;
		
		do {
			stay=true;
			System.out.println("Elija una opción:"
					+ "\n1-Registro en el usuario "+getUser()+";"
					+ "\n2-Registro en usuario personalizado;"
					+ "\n3-Salir de la app;");
			op=sc.nextLine();
			
			try {
				switch(op) {
				case"1":
					registerUserAccess(getUser());
					break;
				case"2":
					registerCustomAccess();
					break;
				case"3":
					stay=false;
					break;
				default:
					System.err.println("Opción incorrecta.Porfavor, inténtelo de nuevo;");
					break;
				}
			}catch(IOException e) {
				writeErrorInErrLog(e.getMessage());
			}
		}while(stay);
		//---------------------------------------------
		sc.close();
		end();
	}
	
	/*This method returns the current date and time 
	 * in format dd-MM-yyyy HH:mm:ss
	 * */
	private static String getTimeStamp() {
		DateFormat formatoFecha=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal=Calendar.getInstance();
		String fecha=formatoFecha.format(cal.getTime());
		return fecha;
	}
	
	
	/*This method creates the basic structure
	 * */
	private static void createStructure() throws IOException {
		File fic=new File("actividad");
		if(!fic.exists()) {
			fic.mkdir();
		}
		fic=new File("net");
		if(!fic.exists()) {
			fic.mkdir();
		}
		fic=new File("err.log");
		if(!fic.exists()) {
			fic.createNewFile();
		}
	}
	
	/*This method write in the file err.log all the Exceptions
	 * that the program could generate
	 * */
	private static void writeErrorInErrLog(String msg) {
		System.out.println("Ha ocurrido el siguiente error. Se registrará en el fichero err.log:");
		System.err.println(msg+"\n");
		msg=getTimeStamp()+"-"+msg+"\n";
		
		BufferedWriter bw = null;
	    FileWriter fw = null;
		 try {
		        File file = new File("err.log");
		        if (!file.exists()) {
		            file.createNewFile();
		        }
		        fw = new FileWriter(file.getAbsoluteFile(), true);
		        bw = new BufferedWriter(fw);
		        bw.write(msg);
		    } catch (IOException e) {
		    	System.out.println(e.getMessage());
		    } finally {
		        try {
		            if (bw != null)
		                bw.close();
		            if (fw != null)
		                fw.close();
		        } catch (IOException e) {
		        	System.out.println(e.getMessage());
		        }
		    }
	}
	
	/*This method returns the Propertiy's name
	 * */
	private static String getUser() {
		return System.getProperty("user.name");
	}
	
	/*This method is used for register a custom name for the logging
	 * */
	private static void registerCustomAccess() throws IOException{
		String user;
		String aux;
		boolean stay;
		do {
			stay=false;
			System.out.println("Escribe el nombre de Usuario del que quieres hacer registro \n(procure introducir caracteres admitidos por Windows)"
					+ "\nSi desea cancelar esciba \"cancel\" o \"c\":");
			user=sc.nextLine();
			user=user.trim();
				aux=user.toLowerCase();
				if(!(aux.equals("cancel") || aux.equals("c")))registerUserAccess(user);
				else System.out.println("Operacion cancelada;");
			
		}while(stay);
	}

	/*This method register a User on the login
	 * */
	private static void registerUserAccess(String user) throws IOException {
		String f="actividad\\"+user+".log";
		
		BufferedWriter bw = null;
	    FileWriter fw = null;
		
	    File file = new File(f);
	    
	    
		if (!file.exists()) {
			file.createNewFile();
		}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(getTimeStamp()+"\n");
	   
	    	try {
	    		if (bw != null)
	    			bw.close();
	    		if (fw != null)
	    			fw.close();
	    	} catch (IOException e) {
	    		System.out.println(e.getMessage());
	    	}
	    System.out.println("registro almacenado exitosamente en el fichero "+user+".log");
	    waitingDog();
	    writeInNetStatLog();
	}
	
	
	/*This method writes in the net folder the output of the command netstat \a 
	 * */
	private static void writeInNetStatLog() throws IOException {
		File f;
		int lastLog=getNumberLastStatLog();
		if(lastLog==0) {
			lastLog=1;
			f=new File("net\\netstat_1.log");
			f.createNewFile();
		}
		String fileToWrite="net\\netstat_"+lastLog+".log";
		int availableLines=200-numLinesOfFile(fileToWrite);
		
		Process p=Runtime.getRuntime().exec("netstat /a");
		InputStreamReader isr=new InputStreamReader(p.getInputStream());
		
		BufferedReader br=new BufferedReader(isr);
		ArrayList<String>lineas=new ArrayList<String>();
		lineas.add(getTimeStamp());
		while(br.readLine()!=null) {
			lineas.add(br.readLine());
		}
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(fileToWrite,true));
		
		for(int i=0;i<lineas.size();i++) {
			if(availableLines>0) {
				bw.write(lineas.get(i)+"\n");
				availableLines--;
			}else {
				lastLog++;
				f=new File("net\\netstat_"+lastLog+".log");
				f.createNewFile();
				bw=new BufferedWriter(new FileWriter("net\\netstat_"+lastLog+".log",true));
				i--;
				availableLines=200;
			}
		}
		
		System.out.println("Líneas añadidas con éxito en la carpeta net");
		bw.close();
	}
	
	
	/*this method renames the netstat_x.log files based on their corresponding 
	 * number so that they start at 1 and progressively scale from 1 to 1
	 * example:
	 * 		   netstat_32.log --> netstat_2.log
	 * 		   netstat_10.log --> netstat_1.log
	 * 	       netstat_40.log --> netstat_3.log
	 * the method is only applied on files that starts with netstat_x being x an
	 * integer number, and ends with the extension .log
	 * */
	private static void reorganiseNetFolder() {
		File f=new File("net");
		String[]pathNames=f.list();
		String aux="";
		boolean isNumeric;
		char aux2;
		ArrayList<String>correctFiles=new ArrayList<String>();
		for (String pathName : pathNames) {
			aux="";
            if(pathName.toLowerCase().startsWith("netstat_")&&pathName.toLowerCase().contains(".log")) {
            	for(int i=8;i<pathName.length()-4;i++) {
            		aux2=pathName.charAt(i);
            		aux+=""+aux2;
            	}
            	isNumeric =  aux.matches("[+-]?\\d*(\\.\\d+)?");
            	if(isNumeric) {
            		correctFiles.add(pathName);
            	}
            }
        }
		
		correctFiles=orderFiles(correctFiles);
		File newName;
		String aux3;
		for(int i=0;i<correctFiles.size();i++) {
			aux3="net\\netstat_";
			aux3+=(i+1);
			aux3+=".log";
			newName=new File(aux3);
			aux="net\\";
			aux+=correctFiles.get(i);
			f=new File(aux);
			f.renameTo(newName);
		}
	}
	
	
	/*This method returns an ArrayList with the correct files order by its names
	 * */
	private static ArrayList<String> orderFiles(ArrayList<String> correctFiles) {
		String aux;
		char aux2;
		int posMin;
		int numMin;
		for(int i=0;i<correctFiles.size();i++) {
			aux="";
			for(int x=8;x<correctFiles.get(i).length()-4;x++) {
        		aux2=correctFiles.get(i).charAt(x);
        		aux+=""+aux2;
        	}
			posMin=i;
			numMin=Integer.parseInt(aux);
			for(int j=i;j<correctFiles.size();j++) {
				aux="";
				for(int x=8;x<correctFiles.get(j).length()-4;x++) {
            		aux2=correctFiles.get(j).charAt(x);
            		aux+=""+aux2;
            	}
				if(Integer.parseInt(aux)<numMin) {
					numMin=Integer.parseInt(aux);
					posMin=j;
				}
			}
			aux=correctFiles.get(i);
			correctFiles.set(i, correctFiles.get(posMin));
			correctFiles.set(posMin, aux);
		}
		return correctFiles;
	}

	
	/*This method returns the last number of the files in the net folder
	 * */
	private static int getNumberLastStatLog() {
		File f=new File("net");
		String[]pathNames=f.list();
		int numMax=0;
		String aux="";
		int numCheck;
		boolean isNumeric;
		char aux2;
		for (String pathName : pathNames) {
			aux="";
            if(pathName.toLowerCase().startsWith("netstat_")&&pathName.toLowerCase().contains(".log")) {
            	for(int i=8;i<pathName.length()-4;i++) {
            		aux2=pathName.charAt(i);
            		aux+=""+aux2;
            	}
            	isNumeric = aux.matches("[+-]?\\d*(\\.\\d+)?");
            	if(isNumeric) {
            		numCheck=Integer.parseInt(aux);
            		if(numCheck>numMax)numMax=numCheck;
            	}
            }
        }
		return numMax;
	}
	
	
	/*This method returns the number of lines of a folder
	 * */
	private static int numLinesOfFile(String ruta) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(ruta));
		int toret=(int)br.lines().count();
		br.close();
		return toret;
	}
	
	
	/*This method generates a cat
	 * */
	private static void end() {
		System.out.println(""
				+ "\n     FIN"
				+ "\n  /\\ ___ /\\"
				+ "\n (  o   o  )"
				+ "\n  \\  >#<  /"
				+ "\n  /       \\"
				+ "\n /         \\      ^"
				+ "\n|           |    // "
				+ "\n \\         /   //"
				+ "\n  ///  ///  --");
	}

	
	/*This method generates a waiting dog
	 * */
	private static void waitingDog() {
		System.out.println(""
				+ "\n                       __"
				+ "\n                     .'  '."
				+ "\n                 _.-'/  |  \\"
				+ "\n    ,        _.-\"  ,|  /  0 `-."
				+ "\n    |\\    .-\"       `--\"\"-.__.'=====================-,"
				+ "\n    \\ '-'`        .___.--._)=========================|"
				+ "\n     \\            .'      |                          |"
				+ "\n      |     /,_.-'        |     PLEASE STAND BY      |"
				+ "\n    _/   _.'(             |     WITH THE WAITING     |"
				+ "\n   /  ,-' \\  \\            |          DOG <3          |"
				+ "\n   \\  \\    `-'            |                          |"
				+ "\n    `-'                   '--------------------------'");
	}
}
