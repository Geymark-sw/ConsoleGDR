package theRiseOfITS.astratto;

import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;


public abstract class Item {
	
	private static int idStatico = 0;
	private int id;
	private String nome;
	private boolean key; // if it is a key item for the lore
	
	public Item( String nome, boolean key) {
		super();
		this.id = idStatico;
		idStatico++;
		this.nome = nome;
		this.key = key;
	}

	public Item() {
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

	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", nome=" + nome + ", key=" + key + "]";
	}
	
	

}
