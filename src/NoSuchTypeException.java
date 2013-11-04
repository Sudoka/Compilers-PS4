public class NoSuchTypeException extends UnsupportedOperationException {
	private static final long serialVersionUID = 1L;
	NoSuchTypeException(String s) {
		Helper.P("Error: "+s);
	}
}
