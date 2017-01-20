import java.util.List;

import javax.json.JsonObject;

import com.iotracks.api.listener.IOFogAPIListener;
import com.iotracks.elements.IOMessage;

public class IOFogAPIListenerImpl implements IOFogAPIListener{

	public void onBadRequest(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("onBadRequest : " + arg0);
	}

	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		System.out.println("onError : " + arg0);
	}

	public void onMessageReceipt(String messageID, long timestamp) {
		System.out.println("sendSensorData Message ID: " + messageID + ", timestamp: " + timestamp);
	}

	public void onMessages(List<IOMessage> arg0) {
		// TODO Auto-generated method stub
		System.out.println("onMessages : " + arg0.size());
	}

	public void onMessagesQuery(long arg0, long arg1, List<IOMessage> arg2) {
		// TODO Auto-generated method stub
		System.out.println("onMessagesQuery : " + arg0 + " + " + arg1 + " + " + arg2.size());
	}

	public void onNewConfig(JsonObject arg0) {
		// TODO Auto-generated method stub
		System.out.println("onNewConfig : " + arg0); // here you can pick up new config
	}

	public void onNewConfigSignal() {
		// TODO Auto-generated method stub
		System.out.println("onNewConfigSignal");
	}

}
