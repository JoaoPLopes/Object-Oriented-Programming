package exceptions;

public class EdgeNextMoveException  extends Exception{
	
	public EdgeNextMoveException() {
		super("There is no Edge to Where the ant can move");
	}
	public EdgeNextMoveException(String message) {
		super(message);
	} 
	

}
