package back.elemMobile;

public class Satellite extends ElementMobile {
	private int attenteSynchro;
	
	private boolean estSynchro;

	public Satellite(Coordonnee hautDroit, Coordonnee basGauche) {
		super(hautDroit, basGauche, Direction.HORIZONTAL);
		this.attenteSynchro = 0;
	}
	
	@Override
	public void synchronisation() {
		if (this.attenteSynchro < super.tempsAttenteSynchro) {
			this.estSynchro = true;
			this.attenteSynchro++;
		} else {
			System.out.println("synchronisation effectu�");
			this.estSynchro = false;
			this.attenteSynchro = 0;
		}
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
