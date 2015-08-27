import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Container {

	private String Id;
	private long Created;
    private String Image;
    private String[] Names;
    private Port[] Ports;
    private String Status;
    
    private String subId;
    private String name;
    private String containerStatus = "-";
    private String url4Client = "0";		//tag "0" => please run container first
    private String url4Web = "0";
    private String date;
	
	@Override
	public String toString() {
		return "Container [Id=" + Id + ", Created=" + Created + ", Image="
				+ Image + ", Names=" + Arrays.toString(Names) + ", Ports="
				+ Arrays.toString(Ports) + ", Status=" + Status + "]";
	}
	
	public String getSubId() {
		return subId;
	}
	public void setSubId() {
		subId = Id.substring(0,12);
	}
	public String getName() {
		return name;
	}
	public void setName() {
		//System.out.println(Names[0].split("/").length);
		name = Names[0].split("/")[2];
	}
	public String getContainerStatus() {
		return containerStatus;
	}
	public void setContainerStatus() {
		containerStatus = Status.startsWith("Up")? 
				"Running for " + Status.substring(3): "Paused" + Status.substring(10);
	}
	public String getUrl4Client() {
		return url4Client;
	}
	public String getUrl4Web() {
		return url4Web;
	}
	public void setUrl() {
		String str0 = Ports[0].getIP() + ":" + Ports[0].getPublicPort();
		String str1 = Ports[1].getIP() + ":" + Ports[1].getPublicPort();
		if(Ports[0].getPrivatePort() == 6080) {
			url4Web = str0 + "/";		// =>../vnc_auto.html authentication?
			url4Client = str1;
		} else {
			url4Web = str1 + "/";
			url4Client = str0;
		}
	}
	public void setDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.format(new Date(Created*1000L));
	}
	public void embody() {
		setSubId();
		setName();
		if (!Status.equals("")) {
			setContainerStatus();
		}
		if (Ports.length != 0) {
			setUrl();
		}
		setDate();
	}
	
	public String toString1() {
		return "Container [subId=" + subId + ", name=" + name
				+ ", containerStatus=" + containerStatus + ", url4Client="
				+ url4Client + ", url4Web=" + url4Web + ", date=" + date + "]";
	}
	
}
