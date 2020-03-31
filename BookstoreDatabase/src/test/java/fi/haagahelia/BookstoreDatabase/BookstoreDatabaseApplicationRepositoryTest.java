package fi.haagahelia.BookstoreDatabase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fi.haagahelia.BookstoreDatabase.domain.Book;
import fi.haagahelia.BookstoreDatabase.domain.BookRepository;
import fi.haagahelia.BookstoreDatabase.domain.Category;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class BookstoreDatabaseApplicationRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void findByAuthorShouldReturnAuthor() {
		List<Book> books = repository.findByAuthor("Ernest Hemingway");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("A Farewell to Arms");
	}

	@Test
	public void createNewBook() {

		Book books = new Book("Stiven King", "Geem Mile", 2001, "12-16-AA", 30.00, new Category("Horror"));
		repository.save(books);
		assertThat(books.getId()).isNotNull();
	}

}