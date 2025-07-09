package theRiseOfITS.concreto.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theRiseOfITS.astratto.Item;
import theRiseOfITS.interfacce.Speakable;

public class Boss extends Mob implements Speakable {

	public Boss(String name, int hp, int atk, int def, List<Item> dropsList, String dialogue) {
		super(name, hp, atk, def, dropsList);
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

}