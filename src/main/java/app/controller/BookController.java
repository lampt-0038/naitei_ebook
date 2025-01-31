package app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

import app.model.Book;
import app.service.BookService;

@Controller
public class BookController {
	private static final Logger logger = Logger.getLogger(BookController.class);

	@Autowired
	private BookService bookService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/books")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("front/book/index");	
		model.addObject("books", bookService.loadBooks());
		return model;
	}
	
	@RequestMapping(value = "/books/{id}")
	public ModelAndView show(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("front/book/show");
		Book book =  bookService.findById(id);
		if (book == null) {
			model.addObject("error", messageSource.getMessage("book.notFound", null, Locale.US));
		} else {
			model.addObject("book", book);
		}
		return model;
	}
}
