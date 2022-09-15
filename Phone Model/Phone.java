package edu.cuny.csi.csc330.lab4;

public class Phone extends Device {
	private final static int MAX_VOLUME = 100;
	private final static int MINIMAL_VOLUME = 0;
	private final static int MAX_BRIGHTNESS = 100;
	private final static int MINIMAL_BRIGHTNESS = 0;
	private int volume;
	private int brightness;
	private PhoneType phoneType;
	private PhoneProvider phoneProvider;

	public Phone (
			PhoneType phoneType,
			PhoneProvider phoneProvider,
			boolean isOn,
			boolean	connectedToWifi,
			int volume,
			int brightness) {
		super (
				isOn,
				phoneType.isSupportingCellularData(), // boolean value of hasCellularData received from enum file
				phoneType.isSupportingWifi(), // boolean value of hasWifi received from enum file
				connectedToWifi);
		
		setVolume(volume);
		setBrightness(brightness);
		this.phoneType = phoneType;
		this.phoneProvider = phoneProvider;
	}

	public void setVolume(int volume) {
		if (volume > MAX_VOLUME) {
			this.volume = MAX_VOLUME;
			// if given volume is over max volume, assume it is at max
		}
		else if (volume < MINIMAL_VOLUME) {
			this.volume = MINIMAL_VOLUME;
			// if given volume is under min volume, assume it is at min
		}
		else {
			this.volume = volume;
			// otherwise return given volume
		}
	}
	
	public int getVolume() {
		return this.volume;
	}
	
	public void setBrightness(int brightness) {
		if (brightness > MAX_BRIGHTNESS) {
			this.brightness = MAX_BRIGHTNESS;
			// if given brightness is over max brightness, assume it is at max
		}
		else if (brightness < MINIMAL_BRIGHTNESS) {
			this.brightness = MINIMAL_BRIGHTNESS;
			// if given brightness is under min brightness, assume it is at min
		}
		else {
			this.brightness = brightness;
			// otherwise return given brightness
		}
	}

	public int getBrightness() {
		return this.brightness;
	}
	
	@Override
	protected void poweringOn() {
		System.out.println(phoneType.getFriendlyName() + " is turning on.");
		System.out.println("Setting volume to " + getVolume() + "% and brightness to " + getBrightness() + "%.\n");
	}

	@Override
	protected void poweringOff() {
		System.out.println(phoneType.getFriendlyName() + " is turning off.");
		System.out.println("Setting volume and brightness to 0.\n");
	}

	@Override
	public String toString() {
		String display = "Device: " + phoneType.getBrand().getFriendlyName() + " " + phoneType.getFriendlyName() + "\nPhone Provider: " + phoneProvider + "\nThis phone is ";
		// get the simplified friendly names of the phone brand and type selected by user
		if (isOn()) {
			display += "turned on.";
			// check to see if device is on, otherwise no reason to check if it is connected to wifi
			if (hasWifi()) {
				// check to see if the device has wifi before determining whether or not it is connected
				display += "\nThis phone is " + (connectedToWifi() ? "connected to the Wifi." : "not connected to the Wifi.");
				// if phone has wifi, print whether it is connected or not
			}
			else {
				display += "\nThis phone does not support Wifi.";
				// if phone does not have wifi, print that it does not support it
			}
		} 
		else {
			display += "turned off.";
		}
		if (hasCellularData()) {
			display += "\nThis phone supports cellular data.";
		} 
		else {
			display += "\nThis phone does not support cellular data.";
		}
		if (isOn()) {
			display += "\nThe volume is at " + getVolume() + "% and the brightness is at " + getBrightness() + "%.";
		}
		else {
			display += "\nThe volume was at " + getVolume() + "% and the brightness was at " + getBrightness() + "%.";
		}
		return display;
	}

	public static void main(String[] args) {
		Phone phone = new Phone(PhoneType.IPHONE, PhoneProvider.VERIZON, true, false, 143, 40);
		System.out.println(phone);
		System.out.println();
		phone.poweringOff();
		
		Phone phone2 = new Phone(PhoneType.RAZOR, PhoneProvider.SPRINT, true, true, 96, 24);
		System.out.println(phone2);
		System.out.println();
		phone2.poweringOff();
		phone2.poweringOn();
		
		Phone phone3 = new Phone(PhoneType.GALAXY, PhoneProvider.T_MOBILE, false, true, -232, 27);
		System.out.println(phone3);
		System.out.println();
		phone3.poweringOn();

	}

}

abstract class Device {
	private boolean isOn;
	private boolean hasCellularData;
	private boolean	hasWifi;
	private boolean	connectedToWifi;

	public Device(boolean isOn, boolean hasCellularData, boolean hasWifi, boolean connectedToWifi) {
		this.isOn = isOn;
		this.hasCellularData = hasCellularData;
		this.hasWifi = hasWifi;
		this.connectedToWifi = connectedToWifi;
		if (isOn()) on();
	}

	public boolean isOn() {
		return isOn;
	}

	public boolean hasCellularData() {
		return hasCellularData;
	}

	public boolean hasWifi() {
		return hasWifi;
	}
	
	public boolean connectedToWifi() {
		if (hasWifi == false) {
			connectedToWifi = false;
			return connectedToWifi;
		}
		else {
			return connectedToWifi;
		}
	}
	
	public void on() {
		if (this.isOn) return;
		this.isOn = true;
		poweringOn();
	}

	public void off() {
		if (!this.isOn) return;
		this.isOn = false;
		poweringOff();
	}

	protected abstract void poweringOn();
	protected abstract void poweringOff();

}



