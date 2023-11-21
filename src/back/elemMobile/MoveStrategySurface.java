package back.elemMobile;

public class MoveStrategySurface extends MoveStrategy {

	@Override
	public void move(ElementMobile elem, int maxX, int minX, int maxY, int minY) {
		Balise balise = (Balise) elem;
		int direction = balise.getIndexDescente() != 0 ? 10 : -10;

		if ((elem.getHautDroit().y > minY && direction < 0) || (elem.getBasGauche().y < maxY && direction > 0)) {
			elem.getBasGauche().setLocation(elem.getBasGauche().x, elem.getBasGauche().y + direction);
			elem.getHautDroit().setLocation(elem.getHautDroit().x, elem.getHautDroit().y + direction);
		}

		if (balise.getIndexDescente() != 0 && direction > 0) {
			balise.setIndexDescente(balise.getIndexDescente() - 1);
			if (balise.getIndexDescente() == 0) {
				balise.setMoveStrategy(balise.getDefaultStrategy());
			}
		}
	}

}
