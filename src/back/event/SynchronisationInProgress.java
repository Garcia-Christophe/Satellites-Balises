package back.event;

import back.mobileElement.MobileElement;

public class SynchronisationInProgress extends AbstractEvent {

	private static final long serialVersionUID = -2325060188548024695L;

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
