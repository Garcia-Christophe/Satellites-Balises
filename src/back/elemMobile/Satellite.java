package back.elemMobile;

public class Satellite extends ElementMobile {

	public Satellite(Coordonnee coordonnee, Direction direction) {
		super(coordonnee, Direction.HORIZONTAL);
	}
	
	@Override
	public void synchronisation() {
		System.out.println("synchronisation effectué");
	}
	
	public void move(int maxX, int minX) {
		this.getCoordonnee().setX(this.getCoordonnee().getX()+1);
		if (this.getCoordonnee().getX() > maxX) 
			this.getCoordonnee().setX(minX);
	}

}
