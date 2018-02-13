import java.net.InetSocketAddress;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.IndividualAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.medium.KNXMediumSettings;
import tuwien.auto.calimero.process.ProcessCommunicator;
import tuwien.auto.calimero.process.ProcessCommunicatorImpl;

public class Test {
	public static void main(String args[]) throws KNXException, InterruptedException{
		
		InetSocketAddress localaddr = InetSocketAddress.createUnresolved("148.60.172.20", 3000);
		InetSocketAddress remoteEP = InetSocketAddress.createUnresolved("192.168.1.10", 3000);
		IndividualAddress IA = new IndividualAddress(1,2,1);
		KNXMediumSettings settings = null;
		settings.setDeviceAddress(IA);
		
			KNXNetworkLinkIP netLinkIP = KNXNetworkLinkIP.newTunnelingLink(localaddr, remoteEP, false,settings);
			ProcessCommunicator pc = new ProcessCommunicatorImpl(netLinkIP);
			
			float Temp = pc.readFloat(new GroupAddress("0/1/0"));
	
	}
}
