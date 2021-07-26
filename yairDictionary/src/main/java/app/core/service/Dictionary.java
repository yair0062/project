package app.core.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entity.Value;
import app.core.repository.ExamplesRepository;
import app.core.repository.ValueRepository;

@Service
@Transactional
public class Dictionary {

	@Autowired
	private ExamplesRepository examplesRepository;

	@Autowired
	private ValueRepository valueRepository;

	public Value addValue(Value value) {
		valueRepository.save(value);
		return value;
	}

	public Value getValue(String word) {
		Value value = valueRepository.findByWord(word);
		System.out.println(value);
		return value;
	}

	public List<Value> getAllValue() {
		List<Value> values = valueRepository.findAll();
		for (Value value : values) {
			System.out.println(value);
		}
		return values;
	}

	public Value updateValue(Value value) {
		Value value2 = valueRepository.getOne(value.getId());
		value2.setDefinition(value.getDefinition());
		value2.setWord(value.getWord());
		value2.setExamples(value.getExamples());
		valueRepository.saveAndFlush(value2);
		return value2;
	}

	public String deletValue(Integer valurId) {
		valueRepository.deleteById(valurId);
		return "the value delete";
	}

	public void deletExample(Integer exampleId) {
		examplesRepository.deleteById(exampleId);
	}

}
