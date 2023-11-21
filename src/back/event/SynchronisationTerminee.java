package back.event;

import back.elemMobile.ElementMobile;

public class SynchronisationTerminee extends AbstractEvent {

	private static final long serialVersionUID = -2325060188548024695L;

	public SynchronisationTerminee(ElementMobile source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		((ElementMobile) target).receive(this);
	}

	@Override
	public String toString() {
		return "Synchronisation terminée";
	}

}
