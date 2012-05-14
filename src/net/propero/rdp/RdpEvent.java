package net.propero.rdp;

public enum RdpEvent {
	INIT("init"),	
	DESTROY("destroy");
	
	private String name;

	RdpEvent(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}

