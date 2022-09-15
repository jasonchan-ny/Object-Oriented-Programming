package edu.cuny.csi.csc330.lab6;

public class QuickPickerException extends Exception {

	public static int INVALID_GAME_NAME = 1; 
	public static int INVALID_PARAMETERS = 2;

	public static String[] MESSAGE = { 
			"Code Unspecified", 	  		
			"Invalid Game Name",
			"Invalid Parameters"
	};
	
	protected int code;
	
	private QuickPickerException() {
	}
	
	public QuickPickerException(String m) {
		super(m);
	}
	
	public QuickPickerException(String message, int code) { 
    	super(message);
    	this.code = code;
    }
    
	public int getCode() { 
		return code;
	}
	
	@Override
	public String toString() {
		return "QuickPickerException [code=" + code + ", toString()="
				+ super.toString() + "]\n" + MESSAGE[code];
	}
	
	public static void main(String[] args) {
		QuickPickerException test = new QuickPickerException("QuickPicker Exception Message ...");	
		System.out.println("Ex: " + test);
	}

}
