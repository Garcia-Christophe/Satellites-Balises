package back.event;

import java.util.EventObject;

/**
 * Classe abstraite représentant la base d'un évènement.
 */
public abstract class AbstractEvent extends EventObject {

	/**
	 * Version UID pour la sérialisation.
	 */
	private static final long serialVersionUID = -693549131041202966L;

	/**
	 * Crée un évènement.
	 * 
	 * @param source - Objet ayant émit l'évènement
	 */
	public AbstractEvent(Object source) {
		super(source);
	}

	/**
	 * Méthode abstraite permettant d'envoyer un évènement.
	 * 
	 * @param target - Objet devant recevoir l'évènement
	 */
	public abstract void sendTo(Object target);

}
