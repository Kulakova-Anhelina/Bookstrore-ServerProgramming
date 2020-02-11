package fi.haagahelia.BookstoreDatabase;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.BookstoreDatabase.domain.Book;
import fi.haagahelia.BookstoreDatabase.domain.BookRepository;
import fi.haagahelia.BookstoreDatabase.domain.Category;
import fi.haagahelia.BookstoreDatabase.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreDatabaseApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreDatabaseApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BookstoreDatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Classic"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Detective"));
			crepository.save(new Category("Fairy tale"));
			crepository.save(new Category("Adventure"));

			log.info("save a couple of books");
			Book y = new Book("Ernest Hemingway", "A Farewell to Arms", 1929, "12-11-AA", 30.00,
					crepository.findByName("Classic").get(0));
			Book y1 = new Book("Erich Maria Remarque", "Three Commarades", 1937, "12-12-AA", 30.00,
					crepository.findByName("Drama").get(0));
			Book y2 = new Book("Andrzej Sapkowski", "The Witcher", 1990, "12-13-AA", 30.00,
					crepository.findByName("Adventure").get(0));

			brepository.save(y);
			brepository.save(y1);
			brepository.save(y2);

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());

			}
			;
		};

	}
}
