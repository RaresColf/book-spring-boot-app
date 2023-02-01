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
import java.util.Optional;

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
          log.info("No books were found!");
          throw new NotFoundException("No books were found");
      }
      log.info("Books found in the database");
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
        log.info("Fetching book with id {}", id);
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()){
            return  optionalBook.get();
        } else {
            throw new NotFoundException(String.format("Book with id (%s) could not be found", id));
        }
    }
}
