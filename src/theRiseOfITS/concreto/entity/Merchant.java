package theRiseOfITS.concreto.entity;

import java.util.List;

import theRiseOfITS.astratto.Entity;
import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Speakable;

public class Merchant extends Entity implements Speakable {

	private List<Item> inventario;
	private String dialogue;

	public Merchant(String nome, int hp, int atk, int def, List<Item> inventario, String dialogue) {
		super(nome, hp, atk, def);
		this.inventario = inventario;
		this.dialogue = dialogue;
	}

	public List<Item> getInventario() {
		return inventario;
	}

	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}

	public void mostraInventario() {
		System.out.println("Inventario di " + this.getName() + ":");
		for (int i = 0; i < inventario.size(); i++) {
			Item item = inventario.get(i);
			System.out.println((i + 1) + ". " + item.getNome());
		}
	}

	public Item vendi(int index) {
		if (index >= 0 && index < inventario.size()) {
			return inventario.get(index);
		}
		return null;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return super.isDead();
	}

	@Override
	public void speak() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDialogue() {
		System.out.println(getName() + " dice: \"" + dialogue + "\"");
		return null;
	}

}
