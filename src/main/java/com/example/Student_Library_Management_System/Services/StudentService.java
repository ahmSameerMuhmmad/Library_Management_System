package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student){
        // Because we are using bidirectional mapping, so we don't need to save card
        // but before saving student in database we have to set basic attribute which are not till set

        // From Card part
        Card card=new Card(); // Card should be autogenerated when createStudent function is called.
        card.setCardStatus(CardStatus.ACTIVATED); // Card status is being set.
        card.setStudentVariableName(student);


        // From Student part
        student.setCard(card);


        /*
        Note- If there will be unidirectional mapping: we have to save both of them
           studentRepository.save() ,  and cardRepository.save()
           but we don't need here because we use bidirectional mapping here
         */

        studentRepository.save(student);
        return "successfully card and student added !";
    }


    public String findNameByEmail(String email){
        Student student=studentRepository.findByEmail(email);
        return student.getName();

    }

    public String updateMob(StudentUpdateMobRequestDto studentUpdateMobRequestDto){

        // Convert the dto to entity as per important rule:

        // First we will try to fetch originalData
        Student originalStudent=studentRepository.findById(studentUpdateMobRequestDto.getId()).get();

        // We will keep the other properties as it : and only change the required parameter

        originalStudent.setMobNo(studentUpdateMobRequestDto.getMobNo());

        // Always entity object is being saved
        studentRepository.save(originalStudent);

        return "Update mobile no. successfully";
    }

}
