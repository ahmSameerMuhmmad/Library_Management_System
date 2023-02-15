package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){
        // I want to get the AuthorEntity >>>
        int authorId=bookRequestDto.getAuthorId();

        // Now I will be fetching the authorEntity
        Author author=authorRepository.findById(authorId).get();

        // Converter
        // We have created this entity so that we can save it into the db.
        Book book=new Book();

        // Basic attributes are being from Dto to the Entity Layer
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());

        // Setting the foreign key attribute in the child class
        book.setAuthor(author);

        // we need to update the listOfBooks written in the present class
        List<Book>currentBooksWritten=author.getBooksWritten();
        currentBooksWritten.add(book);

        // Now the book is to be saved, but also author is also to be saved
        // Why we need to again save (Updating) the author ?? bcz
        //  the author Entity has been updated .....we need to reserve/update it

        authorRepository.save(author);   /// Date was modified

        // .save function works both as saved function and as updated function

        // bookRepository.save is not required : because it will automatically save or update
        /// because of cascading effect

        return "Book Added successfully";


    }
}
