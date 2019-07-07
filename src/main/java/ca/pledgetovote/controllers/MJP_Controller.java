package ca.pledgetovote.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import mcp_models.Call;
import mcp_models.Code_Pojo;
import mcp_models.MSG;

@RestController
public class MJP_Controller {
	private List<Code_Pojo> calls = new ArrayList<>();

	@GetMapping("/hello")
	public String getHello() {
		return "For testing purposes";
	}

	@GetMapping("/first_task/{date}")
	public List<Code_Pojo> getCallsPerDay3(@PathVariable("date")String dateS) {
		List<JSONObject> jsonObject = new ArrayList<>();
		JSONObject obj;
		// The name of the file to open. I couldn`t read from file on GitHub(need a help with that) so I put those files on my pc.
		String fileName = "C:/"+dateS+".txt";
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				obj = (JSONObject) new JSONParser().parse(line);
				jsonObject.add(obj);
				Call call = new Call();
				MSG msg = new MSG();
				if (obj.get("message_type").equals("CALL")) {
					call.setMessage_type((String) obj.get("message_type"));
					if ((obj.get("timestamp").equals("")) || (obj.get("timestamp").equals(null))) {

					} else {
						call.setTimestamp((long) obj.get("timestamp"));

					}

					if ((obj.get("origin").equals("")) || (obj.get("origin").equals(null))) {

					} else {
						call.setOrigin((long) obj.get("origin"));

					}

					if ((obj.get("destination").equals("")) || (obj.get("destination").equals(null))) {

					} else {
						call.setDestination((long) obj.get("destination"));

					}

					if (obj.containsKey("duration")) {
						if ((obj.get("duration").equals("")) || (obj.get("duration").equals(null))) {

						} else {
							call.setDuration((long) obj.get("duration"));

						}
					}

					call.setStatus_code((String) obj.get("status_code"));
					call.setStatus_description((String) obj.get("status_description"));

					calls.add(call);
				}
				else if (obj.get("message_type").equals("MSG")) {
					msg.setMessage_type((String) obj.get("message_type"));
					if (obj.containsKey("timestamp")) {
						if ((obj.get("timestamp").equals("")) || (obj.get("timestamp").equals(null))) {

						} else {
							msg.setTimestamp((long) obj.get("timestamp"));

						}
					}

					if (obj.containsKey("origin")) {
						if ((obj.get("origin").equals("")) || (obj.get("origin").equals(null))) {

						} else {
							msg.setOrigin((long) obj.get("origin"));

						}
					}
					if (obj.containsKey("destination")) {
						if ((obj.get("destination").equals("")) || (obj.get("destination").equals(null))) {

						} else {
							msg.setDestination((long) obj.get("destination"));

						}
					}
					msg.setMessage_content((String) obj.get("message_content"));
					msg.setMessage_status((String) obj.get("message_status"));

					calls.add(msg);
				}
				 else if (obj.get("message_type").equals("")) {
					if ((obj.containsKey("message_content")==true)||(obj.containsKey("message_status")==true)) {
						msg.setMessage_type((String) obj.get("message_type"));
						if (obj.containsKey("timestamp")) {
							if ((obj.get("timestamp").equals("")) || (obj.get("timestamp").equals(null))) {

							} else {
								msg.setTimestamp((long) obj.get("timestamp"));

							}
						}

						if (obj.containsKey("origin")) {
							if ((obj.get("origin").equals("")) || (obj.get("origin").equals(null))) {

							} else {
								msg.setOrigin((long) obj.get("origin"));

							}
						}
						if (obj.containsKey("destination")) {
							if ((obj.get("destination").equals("")) || (obj.get("destination").equals(null))) {

							} else {
								msg.setDestination((long) obj.get("destination"));

							}
						}
						msg.setMessage_content((String) obj.get("message_content"));
						msg.setMessage_status((String) obj.get("message_status"));

						calls.add(msg);
					}
					else {
						call.setMessage_type((String) obj.get("message_type"));
						if ((obj.get("timestamp").equals("")) || (obj.get("timestamp").equals(null))) {

						} else {
							call.setTimestamp((long) obj.get("timestamp"));

						}

						if ((obj.get("origin").equals("")) || (obj.get("origin").equals(null))) {

						} else {
							call.setOrigin((long) obj.get("origin"));

						}

						if ((obj.get("destination").equals("")) || (obj.get("destination").equals(null))) {

						} else {
							call.setDestination((long) obj.get("destination"));

						}

						if (obj.containsKey("duration")) {
							if ((obj.get("duration").equals("")) || (obj.get("duration").equals(null))) {

							} else {
								call.setDuration((long) obj.get("duration"));

							}
						}

						call.setStatus_code((String) obj.get("status_code"));
						call.setStatus_description((String) obj.get("status_description"));

						calls.add(call);
					}
				}
			}
			// Closing stream.
			bufferedReader.close();

		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calls;
	}

}
