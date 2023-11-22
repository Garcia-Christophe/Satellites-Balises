package back.event;

import back.mobileElement.MobileElement;

public class AvailableForSynchronisation extends AbstractEvent {

	private static final long serialVersionUID = -2325060188548024695L;

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
