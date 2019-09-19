package model;

import java.util.ArrayList;

public class BaseDados {

	private static ArrayList<GameInfo> infos = XML.lerXML();
	
	public void salvar(GameInfo info) {
		infos.clear();
		infos.add(info);
		XML.salvarXML(infos);
		
	}

	public GameInfo carregar() {
		if(infos.isEmpty())
			return null;
		return infos.get(0);
	}

}
