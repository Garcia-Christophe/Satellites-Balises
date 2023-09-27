package vue;

import java.io.File;
import java.io.IOException;

import nicellipse.component.NiImage;

public abstract class VueElementMobile extends NiImage {

	/**
	 * ID de la version de série générée.
	 */
	private static final long serialVersionUID = 8314462284805791832L;

	public VueElementMobile(File path) throws IOException {
		super(path);
	}

	public void mettreAJourVue() {
		this.redessine();
	}

	public abstract void redessine();

}
