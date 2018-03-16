import java.util.concurrent.TimeUnit;

import tuwien.auto.calimero.GroupAddress;
import tuwien.auto.calimero.process.ProcessCommunicator;

public class Chenillard extends Thread {
	ProcessCommunicator PC;
	boolean stop;

	public Chenillard(ProcessCommunicator pc) {
		// TODO Auto-generated constructor stub
		this.PC = pc;
		stop = false;
	}

	public void run() {

		try {
			while (!stop) {
				PC.write(new GroupAddress("0/0/1"), false);
				TimeUnit.SECONDS.sleep(1);
				PC.write(new GroupAddress("0/0/1"), true);
				TimeUnit.SECONDS.sleep(1);
				PC.write(new GroupAddress("0/0/1"), false);
				TimeUnit.SECONDS.sleep(1);

				PC.write(new GroupAddress("0/0/2"), true);
				TimeUnit.SECONDS.sleep(1);
				PC.write(new GroupAddress("0/0/2"), false);
				TimeUnit.SECONDS.sleep(1);

				PC.write(new GroupAddress("0/0/3"), true);
				TimeUnit.SECONDS.sleep(1);
				PC.write(new GroupAddress("0/0/3"), false);
				TimeUnit.SECONDS.sleep(1);

				PC.write(new GroupAddress("0/0/4"), true);
				TimeUnit.SECONDS.sleep(1);
				PC.write(new GroupAddress("0/0/4"), false);
			}
		} catch (Exception e) {

		}

	}
	
	public void stopChenillard() {
		this.stop = true;
	}

}
