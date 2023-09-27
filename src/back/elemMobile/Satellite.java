package back.elemMobile;

public class Satellite extends ElementMobile {

	public Satellite(Coordonnee coordonnee, Direction direction) {
		super(coordonnee, Direction.HORIZONTAL);
	}
	
	@Override
	public void synchronisation() {
		System.out.println("synchronisation effectué");
	}

}
