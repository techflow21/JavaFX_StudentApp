/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.sobtech.crud;

import com.sobtech.crud.data.AppQuery;
import com.sobtech.crud.model.Student;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class StudentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStudents();
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
    }
    
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_new;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_update;
    
    @FXML
    private TextField input_firstname;
    @FXML
    private TextField input_lastname;
    @FXML
    private TextField input_phonenumber;
    
    @FXML
    private TableView<Student> tableView;
    
    @FXML
    private Student student;
    
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colFirstname;
    @FXML
    private TableColumn<Student, String> colLastname;
    @FXML
    private TableColumn<Student, String> colPhonenumber;
    
    @FXML
    private TextField input_search;
    
    @FXML
    private void addStudent(){
        Student student = new Student(input_firstname.getText(), input_lastname.getText(), input_phonenumber.getText());
        AppQuery query = new AppQuery();
        query.addStudent(student);
    }
    
    @FXML
    private void showStudents(){
        AppQuery query = new AppQuery();
        ObservableList<Student> list = query.getStudentList();
        colId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        colLastname.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        colPhonenumber.setCellValueFactory(new PropertyValueFactory<Student, String>("phonenumber"));
        
        tableView.setItems(list);
    }
    
    @FXML
    private void mouseClicked(MouseEvent e){
        try{
        Student std = tableView.getSelectionModel().getSelectedItem();
        std = new Student(std.getId(), std.getFirstname(),std.getLastname(), std.getPhonenumber());
        this.student = std;
        
        input_firstname.setText(student.getFirstname());
        input_lastname.setText(student.getLastname());
        input_phonenumber.setText(student.getPhonenumber());
        
        btn_update.setDisable(false);
        btn_delete.setDisable(false);
        btn_save.setDisable(true);
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void updateStudent(){
        try{
            AppQuery query = new AppQuery();
            student = new Student(this.student.getId(), input_firstname.getText(), input_lastname.getText(), input_phonenumber.getText());
            query.updateStudent(student);
            
            showStudents();
            clearFields();
            
            btn_update.setDisable(true);
            btn_delete.setDisable(true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void deleteStudent(){
        try{
            AppQuery query = new AppQuery();
            student = new Student(this.student.getId(), input_firstname.getText(), input_lastname.getText(), input_phonenumber.getText());
            query.deleteStudent(student);
            
            showStudents();
            clearFields();
            
            btn_update.setDisable(true);
            btn_delete.setDisable(true);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void clearFields(){
        input_firstname.setText("");
        input_lastname.setText("");
        input_phonenumber.setText("");   
    }
    
    @FXML
    public void clickNew(){
        clearFields();
        btn_save.setDisable(false);
        
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
    }
}

