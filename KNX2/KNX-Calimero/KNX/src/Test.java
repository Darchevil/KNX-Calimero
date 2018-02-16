import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.IndividualAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.knxnetip.Discoverer;
import tuwien.auto.calimero.knxnetip.KNXnetIPConnection;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.KNXNetworkMonitorIP;
import tuwien.auto.calimero.link.medium.KNXMediumSettings;
import tuwien.auto.calimero.link.medium.TPSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class Test {
	public static final InetSocketAddress localaddr = new InetSocketAddress("192.168.0.106", KNXnetIPConnection.DEFAULT_PORT);
	public static final InetSocketAddress remoteEP = new InetSocketAddress("192.168.0.10",KNXnetIPConnection.DEFAULT_PORT);
	
	
	//***************************************MAIN**************************************
	public static void main(String args[]) throws KNXException, InterruptedException {
	
	connection();
	
	}
		
	
	//***********************************CONNECTION************************************
	public static void connection() throws KNXException, InterruptedException
	{
				
		
			KNXNetworkLinkIP netLinkIP = KNXNetworkLinkIP.newTunnelingLink(localaddr, remoteEP, false,TPSettings.TP1);
			ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIP);
			
			boolean temp = pc.readBool(new GroupAddress("0/0/1"));

			//Lampes
			for(int i =0; i<5; i++) {
				
			pc.write(new GroupAddress("0/0/1"), false);
			TimeUnit.SECONDS.sleep(1);
			pc.write(new GroupAddress("0/0/1"), true);
			TimeUnit.SECONDS.sleep(1);
			pc.write(new GroupAddress("0/0/1"), false);
			TimeUnit.SECONDS.sleep(1);
			
			pc.write(new GroupAddress("0/0/2"), true);
			TimeUnit.SECONDS.sleep(1);
			pc.write(new GroupAddress("0/0/2"), false);
			TimeUnit.SECONDS.sleep(1);
			
			pc.write(new GroupAddress("0/0/3"), true);
			TimeUnit.SECONDS.sleep(1);
			pc.write(new GroupAddress("0/0/3"), false);
			TimeUnit.SECONDS.sleep(1);
			
			pc.write(new GroupAddress("0/0/4"), true);
			TimeUnit.SECONDS.sleep(1);
			pc.write(new GroupAddress("0/0/4"), false);
			
			}
			//Discover connected gateways 
			
			Discoverer disc = new Discoverer(0, true);
			disc.startSearch(100, true);
		
	}
	
}
