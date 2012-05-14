/* RdpApplet.java
 * Component: ProperJavaRDP
 * 
 * Revision: $Revision: 13 $
 * Author: $Author: miha_vitorovic $
 * Date: $Date: 2007-05-11 14:14:45 +0200 (Fri, 11 May 2007) $
 *
 * Copyright (c) 2005 Propero Limited
 *
 * Purpose: Provide an applet interface to ProperJavaRDP
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 * 
 * (See gpl.txt for details of the GNU General Public License.)
 * 
 */

package net.propero.rdp.applet;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.StringTokenizer;

import net.propero.rdp.Common;
import net.propero.rdp.RdpEvent;
import net.propero.rdp.RdpEventPublisher;
import net.propero.rdp.RdpEventSubscriber;

public class RdpApplet extends Applet2 {

	private String id;
	
	public void start() {
		System.out.println("new applet");
		id = getRequiredParameter("id");
		subscribeToRdpEvents();
		
		Common.underApplet = true;
        publishEvent(RdpEvent.INIT, id);
	}
	
	private void subscribeToRdpEvents(){
		RdpEventPublisher.subscribe(new RdpEventSubscriber(){
			
			@Override
			public void destroy(String msg){
				publishEvent(RdpEvent.DESTROY, id, msg);
			}
		});
	}

	private String genFlag(String flag, String parameter) {
		String s = this.getParameter(parameter);
		if (s != null) {
			if (s.equalsIgnoreCase("yes"))
				return flag;
		}
		return "";
	}

	private String genParam(String name, String parameter) {
		String s = this.getParameter(parameter);
		if (s != null) {
			if (name != "")
				return name + " " + s;
			else
				return s;
		} else
			return "";
	}
	
	private String[] getArgs(){
		String argLine = "";
		argLine += genParam("-m", "keymap");
		argLine += " " + genParam("-u", "username");
		argLine += " " + genParam("-p", "password");
		argLine += " " + genParam("-n", "hostname");
		argLine += " " + genParam("-t", "port");
		argLine += " " + genParam("-l", "log_level");
		argLine += " " + genParam("-s", "shell");
		argLine += " " + genParam("-T", "title");
		argLine += " " + genParam("-c", "command");
		argLine += " " + genParam("-d", "domain");
		argLine += " " + genParam("-o", "bpp");
		argLine += " " + genParam("-g", "geometry");
		argLine += " " + genParam("-s", "shell");
		argLine += " " + genFlag("--console", "console");
		argLine += " " + genFlag("--use_rdp4", "rdp4");
		argLine += " " + genFlag("--debug_key", "debug_key");
		argLine += " " + genFlag("--debug_hex", "debug_hex");
		argLine += " " + genFlag("--no_remap_hash", "no_remap_hash");

		argLine += " " + genParam("", "host");

		String[] args;
		StringTokenizer tok = new StringTokenizer(argLine, " ");
		for (Object[] obj = { tok, args = new String[tok.countTokens()],
				new int[] { 0 } }; ((StringTokenizer) obj[0]).hasMoreTokens(); ((String[]) obj[1])[((int[]) obj[2])[0]++] = ((StringTokenizer) obj[0])
				.nextToken()) {
		}
		return args;
	}
}


