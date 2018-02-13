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
	public static void main(String args[]) throws KNXException, InterruptedException{
		
		InetSocketAddress localaddr = InetSocketAddress.createUnresolved("148.60.172.20", 3000);
		InetSocketAddress remoteEP = InetSocketAddress.createUnresolved("192.168.1.10", 3000);
		IndividualAddress IA = new IndividualAddress(1,2,1); //Adresse individuelle
		KNXMediumSettings settings = null;
		settings.setDeviceAddress(IA); //Définition de l'adresse de l'équipement 
		
			KNXNetworkLinkIP netLinkIP = KNXNetworkLinkIP.newTunnelingLink(localaddr, remoteEP, false,settings);
			ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIP);
			
			float l1 = (float) pc.readFloat(new GroupAddress("0/1/1"), false);
			
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
