package back.event;

import back.mobileElement.MobileElement;

/**
 * Évènement "Synchronisation terminée" d'un satellite pour la balise auquel il
 * était en train de se synchroniser.
 */
public class SynchronisationEnded extends AbstractEvent {

	/**
	 * Version UID pour la sérialisation.
	 */
	private static final long serialVersionUID = -2325060188548024695L;

	/**
	 * Crée l'évènement "Synchronisation terminée".
	 * 
	 * @param source - Objet ayant émit l'évènement
	 */
	public SynchronisationEnded(MobileElement source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		((MobileElement) target).receive(this);
	}

	@Override
	public String toString() {
		return "Synchronisation terminée";
	}

}
