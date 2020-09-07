package com.phr.mobile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SendMessage {

	public static String sendSms(String number, String msg) {
		try {
			// Construct data
			// Send data
                        String apiKey = "wPEAkcl0Q23CSpxyeNB7T9dRmVf8sh6I4ntr15gbWYqLJOuiFHC2QNjV0UZOPcX8Khvr6iGp4FuEJs3d";
			HttpURLConnection conn = (HttpURLConnection) new URL("https://www.fast2sms.com/dev/bulk").openConnection();
			String data = "sender_id=FSTSMS&message=" + msg + "&language=english&route=p&numbers=" + number;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("authorization", apiKey);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			System.out.println("SMS Result: " + stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
}