package dk.purplegreen.hello;

public class PersonNotFoundException extends Exception {

	private static final long serialVersionUID = -803320848159558809L;

	public PersonNotFoundException() {
	}

	public PersonNotFoundException(String message) {
		super(message);
	}

}
