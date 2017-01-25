package sendSensorData;

import java.util.List;

import javax.json.JsonObject;

import com.iotracks.api.listener.IOFogAPIListener;
import com.iotracks.elements.IOMessage;

public class IOFogAPIListenerImpl implements IOFogAPIListener{

	public void onBadRequest(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onMessageReceipt(String messageID, long timestamp) {
		System.out.println("sendSensorData Message ID: " + messageID + ", timestamp: " + timestamp);
	}

	public void onMessages(List<IOMessage> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onMessagesQuery(long arg0, long arg1, List<IOMessage> arg2) {
		// TODO Auto-generated method stub
		
	}

	public void onNewConfig(JsonObject arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onNewConfigSignal() {
		// TODO Auto-generated method stub
		
	}

}
