package theRiseOfITS.astratto;


public abstract class Entity {
	
	private static int staticId = 0;
	private int id;
	private String name;
	private int hp;
	private int atk;
	private int def;
	
	public Entity(String name, int hp, int atk, int def) {
		super();
		this.id = staticId;
		staticId++;
		this.name = name;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
	}
	
	public static int getStaticId() {
		return staticId;
	}

	public static void setStaticId(int staticId) {
		Entity.staticId = staticId;
	}

	public boolean isDead() {
		return hp <=0;
	}

	public Entity() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "Entity [ name=" + name + ", hp=" + hp + ", atk=" + atk + ", def=" + def + "]";
	}
	
	//function that allows an entity to do damage to a given mob, 
	//reducing his health if the attack goes through the mob's defence
	public boolean attack(Entity entity) {
		return entity.takeDamage(this.atk);
	}
	
	//function that allows a mob to take damage by a given atk
	//reducing his health if the attack goes through the mob's defence
	public boolean takeDamage(int atk) {
		if (atk >= this.def) {
			this.hp -= (atk - this.def);
			return true;
		} else {
			return false;
		}
	}

}
