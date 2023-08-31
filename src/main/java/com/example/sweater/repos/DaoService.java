package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaoService extends JpaRepository {

	List<DtoJoke> findByType(Type type);

	List<DtoJoke> findTop100ById();

}
