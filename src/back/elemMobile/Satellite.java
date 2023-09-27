package back.elemMobile;

public class Satellite extends ElementMobile {

	public Satellite(Coordonnee hautDroit, Coordonnee basGauche) {
		super(hautDroit, basGauche, Direction.HORIZONTAL);
	}
	
	@Override
	public void synchronisation() {
		System.out.println("synchronisation effectu�");
	}
	
	public void move(int maxX, int minX) {
		this.getBasGauche().setX(this.getBasGauche().getX() + 10);
		this.getHautDroit().setX(this.getHautDroit().getX() + 10);
		if (this.getHautDroit().getX() > maxX) {
			int ecart = this.getHautDroit().getX() - this.getBasGauche().getX();
			this.getBasGauche().setX(minX);
			this.getHautDroit().setX(minX + ecart);
		}
			
	}

}
