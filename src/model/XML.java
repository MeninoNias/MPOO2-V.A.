package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class XML {

	public static void salvarXML(ArrayList<GameInfo> base) {
		XStream xStream = new XStream();
		xStream.alias("Player", GameInfo.class);
		try {
			File file = new File("BancoDados.xml");
			if (!file.exists())	file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			out.write(xStream.toXML(base));
			out.flush();
			out.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static ArrayList<GameInfo> lerXML(){
		FileReader base = null;
		try {
			base = new FileReader("BancoDados.xml"); 
			XStream xStream = new XStream(new Dom4JDriver());
			xStream.alias("Player", GameInfo.class);
			ArrayList<GameInfo> BancoDados = (ArrayList<GameInfo>) xStream.fromXML(base);
			return BancoDados;
		}
		catch (Exception e) {
			return new ArrayList<GameInfo>();
		}
	}
}