package net.propero.rdp.applet;

import java.applet.Applet;

import net.propero.rdp.Rdesktop;
import net.propero.rdp.RdesktopException;

public class RdpAppletThread extends Thread {

	String[] args;

	Applet parentApplet = null;

	public RdpAppletThread(String[] args) {
		this.args = args;
	}

	public void run() {
		this.setPriority(Thread.MAX_PRIORITY);
		try {
			Rdesktop.main(args);
		} catch (RdesktopException e) {
			e.printStackTrace();
		} 
	}
}