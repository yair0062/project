package app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entity.Examples;
import app.core.entity.Value;

public interface ValueRepository extends JpaRepository<Value, Integer> {

	Value findByWord(String word);

}
