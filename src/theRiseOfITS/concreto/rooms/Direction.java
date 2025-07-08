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
}
