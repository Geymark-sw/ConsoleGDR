package theRiseOfITS.astratto;

<<<<<<< HEAD
=======
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
>>>>>>> ef7d75624d253ee149709b8c8edfa6a61d410096

import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

<<<<<<< HEAD
=======
//import com.fasterxml.jackson.annotation.JsonSubTypes;
//
//Questa parte serve a dire che leggendo il file json, quando il type dell'oggetto json 
//è uguale a uno di questi qua sotto (armor, bomb, coin, ecc...),
//deve creare un istanza della classe corrispondente Armor, Bomb, Coin, ecc...
//@JsonTypeInfo(
//  use = JsonTypeInfo.Id.NAME,
//  include = JsonTypeInfo.As.PROPERTY,
//  property = "type"
//)
//@JsonSubTypes({
//  @JsonSubTypes.Type(value = Armor.class, name = "armor"),
//  @JsonSubTypes.Type(value = Bomb.class, name = "bomb"),
//  @JsonSubTypes.Type(value = Coin.class, name = "coin"),
//  @JsonSubTypes.Type(value = Potion.class, name = "potion"),
//  @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
//})
>>>>>>> ef7d75624d253ee149709b8c8edfa6a61d410096


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
