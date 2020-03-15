package fi.haagahelia.BookstoreDatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.BookstoreDatabase.domain.Book;
import fi.haagahelia.BookstoreDatabase.domain.BookRepository;
import fi.haagahelia.BookstoreDatabase.domain.CategoryRepository;

@Controller

public class BookstoreController {
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;

	// Show all students
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// show all the book
	@RequestMapping(value = { "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";

	}

	// RESTful service to get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get student by id
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

	// add book
	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("books", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	// save the book
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// delete book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	// edit book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("books", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";

	}

}
