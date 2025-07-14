package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Speakable;

public class Boss extends Mob implements Speakable {

	private List<Item> dropsList = new ArrayList<Item>();
	
	public Boss(String name, int hp, int atk, int def, List<Item> dropsList, String dialogue) {
		super(name, hp, atk, def, dropsList);
		this.dialogue = dialogue;
	}
	
	public Boss() {
		// TODO Auto-generated constructor stub
	}


	public void setDropsList(List<Item> dropsList) {
		this.dropsList = dropsList;
	}

	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	private String dialogue;

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return super.isDead();
	}

	@Override
	public String getDialogue() {
		return dialogue;
	}

	@Override
	public void speak() {
		System.out.println(getName() + " dice: \"" + dialogue + "\"");
	}

	public List<Item> getListDrop() {
	    
		return dropsList;
	}
}