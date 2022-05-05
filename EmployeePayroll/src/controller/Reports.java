/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.DB;

/**
 *
 * @author MSI-GF63
 */
public class Reports {

    public static void SalaryReport(String fName, String lName, int id, String designation, String department) {

        Connection con = DB.createConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(fName + " " + lName + "-Salary Slip" + ".pdf"));
        int fileChooserResult = fileChooser.showSaveDialog(null);
        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();

            try {
                double dedAmount = 0;
                String reason = "";
                double totalAllowance = 0;
                double salary = 0;
                double ot = 0;
                double medical = 0;
                double bonus = 0;
                double other = 0;

                String sqlDeduction = "SELECT * FROM deduction WHERE empId = '" + id + "'";
                pst = con.prepareStatement(sqlDeduction);
                rs = pst.executeQuery();
                while (rs.next()) {
                    dedAmount = rs.getDouble(6);
                    reason = rs.getString(7);
                }
                rs.close();
                pst.close();

                String sqlAllowance = "SELECT * FROM allowance WHERE empId = '" + id + "'";
                pst = con.prepareStatement(sqlAllowance);
                rs = pst.executeQuery();
                while (rs.next()) {
                    ot = rs.getDouble(4);
                    medical = rs.getDouble(5);
                    bonus = rs.getDouble(6);
                    other = rs.getDouble(7);
                    salary = rs.getDouble(8);
                    totalAllowance = rs.getDouble(10);
                }
                rs.close();
                pst.close();

                String sqlStaff = "SELECT * FROM staff_information WHERE id = '" + id + "'";
                pst = con.prepareStatement(sqlStaff);
                rs = pst.executeQuery();
                while (rs.next()) {
                    salary = rs.getDouble(14);
                }
                rs.close();
                pst.close();
                
                
                double total = salary + totalAllowance - dedAmount;

                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));

                myDocument.open();
                
                myDocument.add(new Paragraph("------------ALPHA WOLF NUTRITION------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("PAY SLIP", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("EMPLOYEE DETAILS", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Name Of Employee : " + fName + " " + lName, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Designation : " + designation, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Department : " + department, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("SALARY", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Basic Salary : " + salary, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Overtime : " + ot, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Medical : " + medical, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Bonus : " + bonus, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Other : " + other, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("DEDUCTION", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Deduction Reason : " + reason, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Deduction Amount : " + dedAmount, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("TOTAL PAYMENT", FontFactory.getFont(FontFactory.TIMES_BOLD, 16, Font.BOLD)));
                myDocument.add(new Paragraph("Total Earnings : " + totalAllowance, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("Net Pay : " + total, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.PLAIN)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

                myDocument.newPage();

                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report generated successfully", "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void EmployeeReport() {

        Connection con = DB.createConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Employees Report.pdf"));
        int fileChooserResult = fileChooser.showSaveDialog(null);
        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();

            try {
                // TODO add your handling code here:

                String sql = "SELECT * FROM Staff_information";

                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                PdfPTable table = new PdfPTable(13);
                myDocument.open();

                float[] columnWidths = new float[]{3, 8, 7, 5, 5, 9, 8, 9, 6, 6, 6, 8, 8};
                table.setWidths(columnWidths);

                table.setWidthPercentage(100);

                myDocument.add(new Paragraph("------------ALPHA WOLF NUTRITION------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));                
                myDocument.add(new Paragraph("Employees List", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                table.addCell(new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("First Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Last Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Date of Birth", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Gender", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Email", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Contact", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Address", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Department", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Job Title", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Date Hired", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Salary", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Status", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));

                while (rs.next()) {

                    table.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(4), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(18), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(5), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(6), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(8), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(11), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(15), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(13), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(14), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(16), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN))));

                }

                myDocument.add(table);
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated", "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void AllowanceReport() {

        Connection con = DB.createConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Employee Allowance Report.pdf"));
        int fileChooserResult = fileChooser.showSaveDialog(null);
        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();

            try {
                String sql = "SELECT * FROM allowance";

                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                PdfPTable table = new PdfPTable(11);
                myDocument.open();

                float[] columnWidths = new float[]{3, 7, 7, 5, 5, 9, 6, 5, 8, 8, 8};
                table.setWidths(columnWidths);

                table.setWidthPercentage(100);

                myDocument.add(new Paragraph("------------ALPHA WOLF NUTRITION------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));                
                myDocument.add(new Paragraph("Employees Allowance List", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                table.addCell(new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Employee ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("First Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Overtime", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Medical", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Bonus", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Other", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Salary", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Rate", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Allowance", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Created By", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));

                while (rs.next()) {

                    table.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(4), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(5), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(6), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(7), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(8), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(9), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(10), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(11), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));

                }

                myDocument.add(table);
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated", "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {

                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void DeductionReport(){
        
        Connection con = DB.createConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;        
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("Employee Salary Deduction Report.pdf"));
        int fileChooserResult = fileChooser.showSaveDialog(null);
        if (fileChooserResult == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();

            try {

                String sql = "SELECT * FROM deduction";

                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                Document myDocument = new Document();
                PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                PdfPTable table = new PdfPTable(8);
                myDocument.open();

                float[] columnWidths = new float[]{3, 7, 7, 5, 5, 9, 6, 5};
                table.setWidths(columnWidths);

                table.setWidthPercentage(100);

                myDocument.add(new Paragraph("------------ALPHA WOLF NUTRITION------------", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD)));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.add(new Paragraph("Employees Salary Deduction List", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                myDocument.add(new Paragraph(new Date().toString()));
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                table.addCell(new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Employee ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("First Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Last Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Salary", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Deduction Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Deduction Reason", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));
                table.addCell(new PdfPCell(new Paragraph("Made By", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD))));

                while (rs.next()) {
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(4), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(5), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(6), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(7), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    table.addCell(new PdfPCell(new Paragraph(rs.getString(8), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));

                }

                myDocument.add(table);
                myDocument.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                myDocument.close();
                JOptionPane.showMessageDialog(null, "Report was successfully generated", "Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    rs.close();
                    pst.close();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }                
    }    
}
