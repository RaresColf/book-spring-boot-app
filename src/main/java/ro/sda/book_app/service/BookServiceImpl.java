package ro.sda.book_app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.book_app.exceptions.NotFoundException;
import ro.sda.book_app.model.Book;
import ro.sda.book_app.repository.BookRepository;
import ro.sda.book_app.service.interfaces.BookService;

import java.nio.file.NotDirectoryException;
import java.util.List;
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
    log.info("Saving book: {}", book);
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
      if(books.isEmpty()) {
          throw new NotFoundException("No books were found");
      }
      return books;
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Book findById(long id) {
        return null;
    }
}
