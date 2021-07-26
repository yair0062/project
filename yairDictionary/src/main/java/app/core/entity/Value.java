package app.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Value {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String word;
	private String definition;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "valId")
	private List<Examples> examples;

	public Value(String word, String definition, List<Examples> examples) {
		super();
		this.word = word;
		this.definition = definition;
		this.examples = examples;
	}

	public Value() {
		super();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public List<Examples> getExamples() {
		return examples;
	}

	public void setExamples(List<Examples> examples) {
		this.examples = examples;
	}

	public void takeExample(Examples example) {
		examples.add(example);
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Value [id=" + id + ", word=" + word + ", definition=" + definition + "]";
	}

}
