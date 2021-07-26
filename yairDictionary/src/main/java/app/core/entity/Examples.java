package app.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Examples {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String example;

	public Examples(String example) {
		super();
		this.example = example;
	}

	public Examples() {
		super();
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Examples [id=" + id + ", example=" + example + "]";
	}

}
