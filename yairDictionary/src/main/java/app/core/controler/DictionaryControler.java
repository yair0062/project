package app.core.controler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entity.Value;
import app.core.service.Dictionary;

@RestController
@RequestMapping("/Dictionary")
public class DictionaryControler {
	@Autowired
	Dictionary dictionary;

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Value value) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(dictionary.addValue(value));

		} catch (Exception e) {
			System.out.println("add method" + e);
			return null;
		}
	}

	@GetMapping("/get all")
	public ResponseEntity<?> getAllValue() {
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(dictionary.getAllValue());
		} catch (Exception e) {
			throw e;
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Value value) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(dictionary.updateValue(value));
		} catch (Exception e) {
			throw e;
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(Integer valueId) {
		try {
			return ResponseEntity.status(HttpStatus.GONE).body(dictionary.deletValue(valueId));
		} catch (Exception e) {
			throw e;
		}
	}

}
