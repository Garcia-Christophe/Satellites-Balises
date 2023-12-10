package back.event;

import back.mobileElement.MobileElement;

/**
 * Évènement "Disponible pour synchronisation" d'un satellite lorsqu'il n'est
 * pas/plus en synchronsiation avec des balises.
 */
public class AvailableForSynchronisation extends AbstractEvent {

	/**
	 * Version UID pour la sérialisation.
	 */
	private static final long serialVersionUID = -2325060188548024695L;

	/**
	 * Crée l'évènement "Disponible pour synchronisation".
	 * 
	 * @param source - Objet ayant émit l'évènement
	 */
	public AvailableForSynchronisation(MobileElement source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		((MobileElement) target).receive(this);
	}

	@Override
	public String toString() {
		return "Disponible pour synchronisation";
	}

}
