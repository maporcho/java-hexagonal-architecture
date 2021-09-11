package org.example.adapters;

import org.example.data.BookDto;
import org.example.entity.Book;
import org.example.mappers.BookMapper;
import org.example.ports.api.BookServicePort;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookJpaAdapter implements BookServicePort {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);

        Book bookSaved = bookRepository.save(book);

        return BookMapper.INSTANCE.bookToBookDto(bookSaved);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return addBook(bookDto);
    }

    @Override
    public List<BookDto> getBooks() {

        List<Book> books = bookRepository.findAll();

        return BookMapper.INSTANCE.bookListToBookDtoList(books);
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()) {
            return BookMapper.INSTANCE.bookToBookDto(book.get());
        }
        return null;
    }
}
