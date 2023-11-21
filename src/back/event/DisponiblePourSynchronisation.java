package back.event;

import back.elemMobile.ElementMobile;

public class DisponiblePourSynchronisation extends AbstractEvent {

	private static final long serialVersionUID = -2325060188548024695L;

	public DisponiblePourSynchronisation(ElementMobile source) {
		super(source);
	}

	@Override
	public void sendTo(Object target) {
		((ElementMobile) target).receive(this);
	}

	@Override
	public String toString() {
		return "Disponible pour synchronisation";
	}

}
