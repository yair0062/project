package app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entity.Examples;

public interface ExamplesRepository extends JpaRepository<Examples, Integer> {

}
