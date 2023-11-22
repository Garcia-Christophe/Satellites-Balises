package back;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import back.event.AbstractEvent;

/**
 * Gestion d'évènements. La classe enregistre/désenregistre des éléments à
 * l'écoute d'un évènement précis, et peut envoyer un évènement aux éléments à
 * son écoute.
 */
public class EventHandler {

	/**
	 * Associations d'évènements aux éléments à leur écoute.
	 */
	Map<Class<? extends AbstractEvent>, Set<Object>> registry = new HashMap<Class<? extends AbstractEvent>, Set<Object>>();

	/**
	 * Envoi un évènement à tous les éléments enregistrés au type de l'évènement.
	 * 
	 * @param event - Évènement à envoyer
	 */
	public void send(AbstractEvent event) {
		Set<Object> l = registry.get(event.getClass());
		if (l == null)
			return;
		Iterator<Object> itor = l.iterator();
		while (itor.hasNext()) {
			event.sendTo(itor.next());
		}
	}

	/**
	 * Enregistre un élément à un évènement en particulier.
	 * 
	 * @param whichEventType - Classe de l'évènement
	 * @param listener       - élément à enregistrer
	 */
	public void registerListener(Class<? extends AbstractEvent> whichEventType, Object listener) {
		Set<Object> set = registry.get(whichEventType);
		if (set == null) {
			set = new HashSet<Object>();
			registry.put(whichEventType, set);
		}
		set.add(listener);
	}

	/**
	 * Désenregistre un élément d'un évènement en particulier.
	 * 
	 * @param whichEventType - Classe de l'évènement
	 * @param listener       - élément à désenregistrer
	 */
	public void unregisterListener(Class<? extends AbstractEvent> whichEventType, Object listener) {
		Set<Object> set = registry.get(whichEventType);
		if (set == null)
			return;
		set.remove(listener);
	}

}
