package back.event;

import back.mobileElement.MobileElement;

/**
 * Évènement "Synchronisation en cours" d'une balise lorsqu'elle se synchronise
 * avec un satellite disponible.
 */
public class SynchronisationInProgress extends AbstractEvent {

	/**
	 * Version UID pour la sérialisation.
	 */
	private static final long serialVersionUID = -2325060188548024695L;

	/**
	 * Crée l'évènement "Synchronisation en cours".
	 * 
	 * @param source - Objet ayant émit l'évènement
	 */
	public SynchronisationInProgress(MobileElement source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		((MobileElement) target).receive(this);
	}

	@Override
	public String toString() {
		return "Synchronisation en cours";
	}

}
