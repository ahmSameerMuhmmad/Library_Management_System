package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        // Note :---> in the params: the object i of type DTO but the rapisotry
        //             interacts only with entities

        // so solution is to convert authorEntryDto to Author as entity

        // Created an object of type Author
        Author author =new Author();


        // now we will save values of postman(dto) to db
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        // Now save to the db
         authorRepository.save(author);

         return "Author added successfully";
    }
}
