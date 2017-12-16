package isa.project.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.domain.Greeting;
import isa.project.service.GreetingService;

/*
 * @RestController je anotacija nastala od @Controller tako da predstavlja bean komponentu.
 */
@RestController
public class GreetingController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GreetingService greetingService;

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara unutar @RequestMapping anotacije:
	 * url, metod i u slucaju GET zahteva atribut produces sa naznakom tipa odgovora
	 * (u nasem slucaju JSON).
	 * Kao povratna vrednost moze se vracati klasa ResponseEntity koja sadrzi i telo (sam podatak)
	 * i zaglavlje (metapodatke) i status kod, ili samo telo ako se metoda anotira sa @ResponseBody.
	 */
	@RequestMapping(
			value = "/api/greetings",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		logger.info("> getGreetings");

		Collection<Greeting> greetings = greetingService.findAll();

		logger.info("< getGreetings");
		return new ResponseEntity<Collection<Greeting>>(greetings,
				HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/greetings/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
		logger.info("> getGreeting id:{}", id);
		Greeting greeting = greetingService.findOne(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		logger.info("< getGreeting id:{}", id);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	/*
	 * Prilikom poziva metoda potrebno je navesti nekoliko parametara unutar @RequestMapping anotacije:
	 * url, metod i u slucaju POST zahteva atribut produces sa naznakom tipa odgovora
	 * (u nasem slucaju JSON) i atribut consumes sa naznakom oblika u kojem se salje podatak
	 * (u nasem slucaju JSON).
	 * Anotiranjem parametra sa @RequestBody Spring ce pokusati od prosledjenog JSON podatka da
	 * napravi objekat tipa Greeting.
	 */
	@RequestMapping(
			value = "/api/greetings",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(
			@RequestBody Greeting greeting) throws Exception {
		logger.info("> createGreeting");
		Greeting savedGreeting = greetingService.create(greeting);
		logger.info("< createGreeting");
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(
			value = "/api/greetings/{id}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(
			@RequestBody Greeting greeting) throws Exception {
		logger.info("> updateGreeting id:{}", greeting.getId());
		Greeting updatedGreeting = greetingService.update(greeting);
		if (updatedGreeting == null) {
			return new ResponseEntity<Greeting>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("< updateGreeting id:{}", greeting.getId());
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/api/greetings/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Greeting> deleteGreeting(
			@PathVariable("id") Long id) {
		logger.info("> deleteGreeting id:{}", id);
		greetingService.delete(id);
		logger.info("< deleteGreeting id:{}", id);
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}

}
