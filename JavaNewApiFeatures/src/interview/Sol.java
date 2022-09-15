package interview;

/*
 * Class.forName(package);
 * new KeyWord
 * serialization and deserialization
 * 
 */
public class Sol {

	private static Sol type = null;

	private Sol() {
		if (type != null) {
			throw new IllegalStateException("singleton only");
		}
	}

	public static Sol getSol() {
		synchronized (Sol.class) {
			if (type != null) {
				return type;
			}
			type = new Sol();
		}
		return type;
	}
}
