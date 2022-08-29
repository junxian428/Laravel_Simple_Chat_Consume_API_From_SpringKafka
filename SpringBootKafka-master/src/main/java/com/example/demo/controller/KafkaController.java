package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Producer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/kafkaapp")
public class KafkaController {

	@Autowired 
	Producer producer;
	
	@PostMapping(value="/post")
	public void sendMessage(@RequestParam("msg") String msg) {
		producer.publishToTopic(msg);
	}

	@GetMapping(value="/chat")
	public String getMessage(){
		JSONObject jsonObject = new JSONObject();
		//try {
			//File myObj = new File("log.txt");
			//Scanner myReader = new Scanner(myObj);
			//while (myReader.hasNextLine()) {
			  //String data = myReader.nextLine();
			  //System.out.println(data);
			  //jsonObject.put("User X:", data);
			//}
			//myReader.close();
		  //} catch (FileNotFoundException e) {
			//System.out.println("An error occurred.");
			//e.printStackTrace();
		 // }
		File file=new File("log.txt");    //creates a new file instance  
		try (FileReader fr = new FileReader(file)) {
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line;  
			ArrayList<String> chats = new ArrayList<String>(); // Create an ArrayList object
			while((line=br.readLine())!=null)  
			{  
				sb.append(line);      //appends line to string buffer  
				sb.append("\n");     //line feed
				chats.add(line);   
			}  
			fr.close();    //closes the stream and release the resources  
			System.out.println("Contents of File: ");  
			System.out.println(sb.toString());   //returns a string that textually represents the object  
			//System.out.println(sb.);
			//jsonObject.put("User X:", sb.toString());
			GsonBuilder gsonBuilder = new GsonBuilder();
        
			// This is the main class for using Gson. Gson is typically used by first constructing a Gson instance and then invoking toJson(Object) or fromJson(String, Class) methods on it. 
			// Gson instances are Thread-safe so you can reuse them freely across multiple threads.
			Gson gson = gsonBuilder.create();
			String JSONObject = gson.toJson(chats);
			//log("\nConverted JSONObject ==> " + JSONObject);
			
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String prettyJson = prettyGson.toJson(chats);
			return prettyJson;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(jsonObject);
		return "Hello";
		
	}
}
