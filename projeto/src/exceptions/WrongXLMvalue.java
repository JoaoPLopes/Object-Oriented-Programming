package exceptions;

public class WrongXLMvalue extends Exception {
	
	public WrongXLMvalue() {
		super("There is no Edge to Where the ant can move");
	}
	public WrongXLMvalue(String message) {
		super(message);
	} 

}
