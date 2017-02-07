/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sameer
 */
public class TestTry {

	
    public static void main(String args[]) {
      Properties prop = new Properties();
      InputStream input = null;
        try {
            input = TestTry.class.getClassLoader().getResourceAsStream("application.properties");
           // input = new FileInputStream("application.properties");
       
        // load a properties file
        prop.load(input);
    } catch (Exception ex) {
        ex.printStackTrace();
        Logger.getLogger(TestTry.class.getName()).log(Level.SEVERE, null, ex);
    }

		// get the property value and print it out
		System.out.println(prop.getProperty("database"));
		System.out.println(prop.getProperty("dbuser"));
		System.out.println(prop.getProperty("dbpassword"));

    }
}
