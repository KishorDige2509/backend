package interview;

import java.io.Serializable;

/*
 * Class.forName(qualified name of class);
 * new KeyWord
 * serialization and deserialization
 * 
 */
public class PureSingleton implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static PureSingleton instance = null;

	private PureSingleton() {
		if (instance != null) {
			throw new IllegalStateException("singleton only");
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	protected Object readResolve() {
		return instance;
	}

	public static synchronized PureSingleton getSol() {
		if (instance == null) {
			return instance = new PureSingleton();
		} else {
			return instance;
		}
	}
}
