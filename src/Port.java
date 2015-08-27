
public class Port {
	
    private int PrivatePort;
    private int PublicPort;
    private String Type;
    private String IP;

    public int getPrivatePort() {
		return PrivatePort;
	}
	public int getPublicPort() {
		return PublicPort;
	}
	public String getIP() {
		return IP;
	}

	public String toString() {
        return IP + ":" + PublicPort + "->" + PrivatePort + 
        		"/" + Type;
    }
    
}
