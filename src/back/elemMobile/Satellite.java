package back.elemMobile;

public class Satellite extends ElementMobile {
	private int attenteSynchro;
	
	private boolean estSynchro;

	public Satellite(Coordonnee hautDroit, Coordonnee basGauche) {
		super(hautDroit, basGauche, new MoveStrategySatellite());
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

}
