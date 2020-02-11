package fi.haagahelia.BookstoreDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.BookstoreDatabase.domain.Book;
import fi.haagahelia.BookstoreDatabase.domain.BookRepository;
import fi.haagahelia.BookstoreDatabase.domain.CategoryRepository;

@Controller

public class BookstoreController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
		
	

	 @RequestMapping(value={"/", "/booklist"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";

	}

	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("books", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}",  method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("books", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";

	}

}
