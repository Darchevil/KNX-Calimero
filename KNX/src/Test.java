import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import tuwien.auto.calimero.CloseEvent;
import tuwien.auto.calimero.FrameEvent;
import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.IndividualAddress;
import tuwien.auto.calimero.KNXException;
import tuwien.auto.calimero.cemi.CEMILData;
import tuwien.auto.calimero.datapoint.Datapoint;
import tuwien.auto.calimero.datapoint.StateDP;
import tuwien.auto.calimero.dptxlator.DPTXlator;
import tuwien.auto.calimero.dptxlator.DPTXlatorBoolean;
import tuwien.auto.calimero.knxnetip.Discoverer;
import tuwien.auto.calimero.knxnetip.KNXnetIPConnection;
import tuwien.auto.calimero.link.KNXNetworkLink;
import tuwien.auto.calimero.link.KNXNetworkLinkIP;
import tuwien.auto.calimero.link.KNXNetworkMonitorIP;
import tuwien.auto.calimero.link.NetworkLinkListener;
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
			final GroupAddress button1 = new GroupAddress("0/2/0"); //Bouton poussoir
			final GroupAddress buttonArret = new GroupAddress("0/2/2"); //Bouton poussoir
			boolean state;
			Chenillard C = new Chenillard(pc);
			
			
		     
			
			
			//Lampes			
			
			//Ajout d'un bouton de démarrage
			//Message de démarrage 
			System.out.println("Go !");
			
			
			//********************************Listener**************************************
			//Instanciation of NetworkLinkListener
			
			
			NetworkLinkListener listener = new NetworkLinkListener() {
				
				@Override
				public void linkClosed(CloseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void indication(FrameEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println("srcadress : " + arg0.getSource());
					System.out.println("targetadress : " + ((CEMILData) arg0.getFrame()).getDestination());
					System.out.println("Message Code " + arg0.getFrame().getMessageCode());
					
					if((boolean) ((CEMILData) arg0.getFrame()).getDestination().equals(button1)) //Condition de démarrage Si on détecte l'appui sur le 1er bouton poussoir
						{
						try
							{
								C.start();
								
							}
						catch(Exception e)
							{
							
							}
						
						}
					if ((boolean) ((CEMILData) arg0.getFrame()).getDestination().equals(buttonArret))
					{
						C.stopChenillard();
					}
																
						
				
				}
				
				@Override
				public void confirmation(FrameEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			};
			while(true) {
			//AddLinkListener
			netLinkIP.addLinkListener(listener); //Enregistrement 
			}
		
			
	}
	
} 
