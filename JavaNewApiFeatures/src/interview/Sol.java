package interview;

import java.io.Serializable;

/*
 * Class.forName(package);
 * new KeyWord
 * serialization and deserialization
 * 
 */
public class Sol implements Cloneable, Serializable {

	private static Sol instance = null;

	private Sol() {
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

	public static synchronized Sol getSol() {
		if (instance == null) {
			return instance = new Sol();
		} else {
			return instance;
		}
	}
}
