package theRiseOfITS.concreto.entity;

import java.util.List;
import java.util.Random;

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
	
	public void assegnaPrezziCasuali(int prezzoMin, int prezzoMax) {
		Random random = new Random();
		for (Item item : inventario) {
			int prezzo = random.nextInt(prezzoMax - prezzoMin + 1) + prezzoMin;
			item.setPrice(prezzo);
		}
	}

	public void mostraInventario() {
		System.out.println("Inventario di " + this.getName() + ":");
		for (int i = 0; i < inventario.size(); i++) {
			Item item = inventario.get(i);
			System.out.println((i + 1) + ". " + item.getNome() + " - Prezzo: " + item.getPrice());
		}
	}
	
	public boolean vendi(int index, Player giocatore) {
	    if (index >= 0 && index < inventario.size()) {
	        Item item = inventario.get(index);
	        int prezzo = item.getPrice();

	        if (giocatore.getValue() >= prezzo) {
	            giocatore.buyItem(item);
	            giocatore.raccogliItem(item);
	            inventario.remove(index);  // rimuovo l'item venduto
	            System.out.println(giocatore.getName() + " ha acquistato " + item.getNome() + " per " + prezzo + " monete.");
	            return true;
	        } else {
	            System.out.println("Non hai abbastanza monete per acquistare " + item.getNome());
	            return false;
	        }
	    }
	    System.out.println("Indice oggetto non valido.");
	    return false;
	}
	
	

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return super.isDead();
	}

	@Override
	public void speak() {
		System.out.println(getName() + " dice: \"" + dialogue + "\"");

	}

	@Override
	public String getDialogue() {
		return dialogue;
	}

}
