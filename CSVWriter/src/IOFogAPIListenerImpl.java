
import java.util.List;

import javax.json.JsonObject;

import com.iotracks.api.listener.IOFogAPIListener;
import com.iotracks.elements.IOMessage;

public class IOFogAPIListenerImpl implements IOFogAPIListener{
	CSVWriter writer = new CSVWriter();
	public IOFogAPIListenerImpl(CSVWriter writer) {
		this.writer = writer;
	}

	public void onBadRequest(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onMessageReceipt(String messageID, long timestamp) {
		System.out.println("CSVWriter Message ID: " + messageID + ", timestamp: " + timestamp);
	}

	public void onMessages(List<IOMessage> msgs) {
		System.out.println("CSVWriter inside onMessages");
		IOMessage msg = msgs.get(0);
		JsonObject json = msg.getJson(false);
		this.writer.writeCSV(json);
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
