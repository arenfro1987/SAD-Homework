package com.rconyac.microminer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppletToServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/x-java-serialized-object");
		try {
			InputStream inputStream = request.getInputStream();
			ObjectInputStream objectIn = new ObjectInputStream(inputStream);
			String inputString = (String) objectIn.readObject();
			
			OutputStream outputStream = response.getOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
			objectOut.writeObject(inputString);
			objectOut.flush();
			objectOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
