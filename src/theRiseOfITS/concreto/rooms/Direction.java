package theRiseOfITS.concreto.rooms;

public enum Direction {

	NORD, SUD, EST, OVEST;

	public Direction opposite() {
		return switch (this) {
		case NORD -> SUD;
		case SUD -> NORD;
		case EST -> OVEST;
		case OVEST -> EST;
		};

	}

	public Point increment() {
		return switch (this) {
		case NORD -> new Point(0, 1);
		case SUD -> new Point(0, -1);
		case EST -> new Point(1, 0);
		case OVEST -> new Point(-1, 0);
		};

	}
}
