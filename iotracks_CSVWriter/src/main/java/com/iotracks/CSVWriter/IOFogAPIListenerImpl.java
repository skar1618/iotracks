package com.iotracks.CSVWriter;

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

	public void onMessageReceipt(String arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onMessages(List<IOMessage> msgs) {
		IOMessage msg = msgs.get(0);
		JsonObject json = msg.getJson();
		CSVWriter writer = new CSVWriter();
		writer.writeCSV(json);
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
