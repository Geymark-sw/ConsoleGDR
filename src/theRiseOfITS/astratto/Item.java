package theRiseOfITS.astratto;


import theRiseOfITS.concreto.items.Armor;
import theRiseOfITS.concreto.items.Bomb;
import theRiseOfITS.concreto.items.Coin;
import theRiseOfITS.concreto.items.Potion;
import theRiseOfITS.concreto.items.Weapon;

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



public abstract class Item {
	
	private static int idStatico = 0;
	private int id;
	private String nome;
	private int price = 0;
	private boolean key; // if it is a key item for the lore
	private boolean raccolto = false;
	
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

	public static int getIdStatico() {
		return idStatico;
	}

	public static void setIdStatico(int idStatico) {
		Item.idStatico = idStatico;
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

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	
	public boolean isRaccolto() {
		return raccolto;
	}

	public void setRaccolto(boolean raccolto) {
		this.raccolto = raccolto;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", nome=" + nome + ", key=" + key + "]";
	}
	
	

}
