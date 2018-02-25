
package IOStreams.H_Ch8_Streams.AndrasSample;

import java.io.StreamTokenizer;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class InvalidInputException extends Exception{
public String token;
	public InvalidInputException() {
		super();
	}
	
	public InvalidInputException(String errorMsg, String token){
		super(errorMsg);
		this.token = token;
	}

}
