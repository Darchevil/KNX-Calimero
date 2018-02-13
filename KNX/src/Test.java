import java.net.InetSocketAddress;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.IndividualAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.knxnetip.Discoverer;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.KNXMediumSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class Test {
	public static void main(String args[]) throws KNXException, InterruptedException {
			connection();	
	
	}
	public static void connection() throws KNXException, InterruptedException
	{
		InetSocketAddress localaddr = InetSocketAddress.createUnresolved("148.60.172.20", 3671);
		InetSocketAddress remoteEP = InetSocketAddress.createUnresolved("192.168.0.10", 3671);
		IndividualAddress IA = new IndividualAddress(0, 0, 0);
		KNXMediumSettings settings = null;
		settings.setDeviceAddress(IA);
		
			KNXNetworkLinkIP netLinkIP = KNXNetworkLinkIP.newTunnelingLink(localaddr, remoteEP, false,settings);
			ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIP);
			
			double temp = pc.readFloat(new GroupAddress("0/1/0"),false);
			
			//Lampes
			pc.write(new GroupAddress("0/0/1"), true);
			pc.write(new GroupAddress("0/0/2"), true);
			pc.write(new GroupAddress("0/0/3"), true);
			pc.write(new GroupAddress("0/0/4"), true);
			
			//retour état 
			pc.readFloat(new GroupAddress("0/1/0"), true);
			pc.readFloat(new GroupAddress("0/1/1"), true);
			pc.readFloat(new GroupAddress("0/1/2"), true);
			pc.readFloat(new GroupAddress("0/1/3"), true);
			
			
			//Boutons poussoirs 
			pc.write(new GroupAddress("0/2/0"), true);
			pc.write(new GroupAddress("0/2/1"), true);
			pc.write(new GroupAddress("0/2/2"), true);
			pc.write(new GroupAddress("0/2/3"), true);
			
			//Discover connected gateways 
			
			Discoverer disc = new Discoverer(0, true);
			disc.startSearch(100, true);
		
	}
}
