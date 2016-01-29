package com.grisaf.mqttsample;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

import android.util.Log;
import android.widget.TextView;


public class MQTTUtils {

	private static MqttClient client;

	public static MqttClient getClient() {
		return client;
	}
	
	//Establishing the connection with the server

	public static boolean connect(String url) {
		try {
			MemoryPersistence persistance = new MemoryPersistence();
			client = new MqttClient("tcp://" + url + ":1883", "client1", persistance);
			client.connect();
			//client.subscribe("chat/+");
			sub("chat/+");
			
			
			return true;
		} catch (MqttException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	//Sending messages to a particular topic
	
	public static boolean pub(String topic, String sendMsg) {
		MqttMessage message = new MqttMessage(sendMsg.getBytes());
		try {
			client.publish(topic, message);
	
			return true;
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	//Subscribing to all the topics & Receiving messages from them
	
	public static boolean sub(String topic) {
		try {
			
			client.subscribe(topic);
			client.setCallback(new MqttCallback() {
				
				//The message receives here
	            public void messageArrived(String topic, MqttMessage message) throws Exception {
	               Log.d("Received",topic + ": " + message);
	               
	            }
	 
	            
	            public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete 
	            }

				@Override
				public void connectionLost(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
	            
	        });

			return true;
			
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
