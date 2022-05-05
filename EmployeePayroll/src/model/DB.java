/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author MSI-GF63
 */
public class DB {
    public static Connection createConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/EMP_PAYROLL?autoreconnect=true&useSSL=false";
            Connection con = DriverManager.getConnection(url, "root", "");
            return con;
        }
        catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            return null;
        }
    }
}
