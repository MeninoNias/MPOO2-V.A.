package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class BasePerguntas {

	private static ArrayList<Pergunta> perguntas = carregarPerguntas();

	public boolean addPegunta(Pergunta pergunta) {

		return perguntas.add(pergunta);
	}

	public static void salvarPerguntas(ArrayList<Pergunta> base) {
		XStream xStream = new XStream();
		xStream.alias("Pergunta", Pergunta.class);
		try {
			File file = new File("BancoPergunta.xml");
			if (!file.exists())	file.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			out.write(xStream.toXML(base));
			out.flush();
			out.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static ArrayList<Pergunta> carregarPerguntas() {
		FileReader base = null;
		try {
			base = new FileReader("BancoPergunta.xml"); 
			XStream xStream = new XStream(new Dom4JDriver());
			xStream.alias("Pergunta", Pergunta.class);
			ArrayList<Pergunta> BancoDados = (ArrayList<Pergunta>) xStream.fromXML(base);
			return BancoDados;
		}
		catch (Exception e) {
			return new ArrayList<Pergunta>();
		}
	}
}
