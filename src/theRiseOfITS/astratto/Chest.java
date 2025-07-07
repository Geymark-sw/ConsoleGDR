package theRiseOfITS.astratto;

import java.util.List;

public abstract class Chest {
	private static int StaticId = 0;
	private int id;
	private String nome;
	private List<Item> oggettiContenuti;

	public Chest(String nome, List<Item> oggettiContenuti) {
		super();
		this.id = StaticId;
		StaticId++;
		this.nome = nome;
		this.oggettiContenuti = oggettiContenuti;
		
	}
	
	

	public Chest() {
	}



	public static int getStaticId() {
		return StaticId;
	}

	public static void setStaticId(int staticId) {
		StaticId = staticId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Item> getOggettiContenuti() {
		return oggettiContenuti;
	}

	public void setOggettiContenuti(List<Item> oggettiContenuti) {
		this.oggettiContenuti = oggettiContenuti;
	}

	
}
