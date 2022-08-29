package com.example.demo.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
  
	@KafkaListener(topics="mytopic", groupId="mygroup")
	public void consumeFromTopic(String message) throws IOException {
		System.out.println("Consummed message "+message);
		File log = new File("log.txt");

	try{
    	if(!log.exists()){
        	System.out.println("We had to make a new file.");
        	log.createNewFile();
    	}

    	FileWriter fileWriter = new FileWriter(log, true);
    	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    	bufferedWriter.write(message + "\n");
    	bufferedWriter.close();

    	System.out.println("Done");
	} catch(IOException e) {
    	System.out.println("COULD NOT LOG!!");
	}
	
	}
}
