package net.propero.rdp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RdpEventPublisher {

	static Logger logger = Logger.getLogger(RdpEventPublisher.class);
	
	private static List<RdpEventSubscriber> subscribers = new ArrayList<RdpEventSubscriber>();
	
	public static void subscribe(RdpEventSubscriber listener){
		subscribers.add(listener);
	}
	
	public static void publish(RdpEvent e, String txt){
		publish(e, txt, null);
	}
	
	public static void publish(RdpEvent e, String txt, Exception ex){
		logger.info("publish: " + e + " " + txt + (ex != null ? " " + ex : ""));
		
		switch(e){
		case DESTROY:
			for(RdpEventSubscriber s : subscribers){s.destroy(txt);}
			break;			
		default:
			System.err.println("WTF?");
		}	
	}
}
