/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.model;
import console.entity.Student;
import java.util.ArrayList;

/**
 *
 * @author ADMIN-PC
 */
public class StudentModel {
    private static ArrayList<Student> listStudent;
    
    //Get the student list
    public void getList(){
        for(Student stuSample : listStudent){
            System.out.println("Name: "+ stuSample.getName()+"\nEmail: "+stuSample.getEmail() +" \nID: "+stuSample.getId());
            System.out.println("");
        }
    }
    public void insert(Student student) {
        if(listStudent == null){
            listStudent = new ArrayList<>();
        }
        listStudent.add(student);
    }

    public void edit(long i,Student student) {
        int cnt=0;
     for(Student stuSample : listStudent){
         if(i==stuSample.getId()){
             stuSample.setId(System.currentTimeMillis());
             stuSample.setName(student.getName());
             stuSample.setEmail(student.getEmail());
             cnt++;
             break;
         }
     }
     if(cnt==0) System.err.println("There is no student with that ID!!!");
    }

    public void delete(long i) {
        int cnt =0;
      for(int x =0;x<listStudent.size();x++){
          if(i==listStudent.get(x).getId()){
              listStudent.remove(x);
              cnt++;
              return;
          }
      }
      if(cnt==0){
          System.err.println("There is no student as you wish to remove!!!");
      }
    }

}
