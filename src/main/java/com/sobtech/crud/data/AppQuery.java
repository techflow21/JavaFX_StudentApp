/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sobtech.crud.data;

//private Connection MySQLConnection connect;

import com.sobtech.crud.model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AppQuery {
    MySQLConnection conn = new MySQLConnection();
    private PreparedStatement ps;
    
    public void addStudent(Student student){
        try{
            String sql = "INSERT INTO students (firstname, lastname, phonenumber) VALUES(?,?,?)";
            ps = conn.getConnection().prepareStatement(sql);
            
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getPhonenumber());
            
            ps.execute();
            ps.close();
            conn.closeConnection();
            
        }
        catch(Exception e){
          e.printStackTrace();
        }
    }
    
    
    public ObservableList<Student> getStudentList(){
        ObservableList<Student>studentList = FXCollections.observableArrayList();
        try{
            
            String sql = "SELECT id, firstname, lastname, phonenumber FROM students ORDER BY lastname ASC";
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            Student student;
            while(rs.next()){
                student = new Student(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("phonenumber"));
                studentList.add(student);
            }
            rs.close();
            st.close();
            conn.closeConnection();
        }catch(Exception e){
            e.printStackTrace();
        }
        return studentList;
    }
    
    public void updateStudent(Student student){
        try
        {
            String sql = "UPDATE students SET firstname = ?, lastname = ?, phonenumber = ? WHERE id = ?";
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, student.getFirstname());
            ps.setString(2, student.getLastname());
            ps.setString(3, student.getPhonenumber());
            ps.setInt(4, student.getId());
            
            ps.execute();
            ps.close();
            conn.closeConnection();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void deleteStudent(Student student){
        try{
            String sql = "DELETE FROM students WHERE id = ?";
            ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, student.getId());
            
            ps.execute();
            ps.close();
            conn.closeConnection();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}


//public void loginAccount(){
//        String sql = "SELECT Username, Password FROM admin WHERE Username = ? and Password = ?";
//        connect = MySQLConnection.getConnection();
//
//        try{
//            if(li_username.getText().isEmpty() || li_password.getText().isEmpty()){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Massage");
//                alert.setHeaderText(null);
//                alert.setContentText("Fill in all the fields and Try Again!");
//                alert.showAndWait();
//            }else{
//                    statement = connect.prepareStatement(sql);
//                    statement.setString(1, li_username.getText());
//                    statement.setString(2, li_password.getText());
//                    result = statement.executeQuery();
//
//                    if(result.next()){
//                        // Do Stuffs
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Success Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Login Successful!");
//                        alert.showAndWait();
//                    }else{
//                        alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error Massage");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Incorrect Username/Password, Try Again!");
//                        alert.showAndWait();
//                    }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }