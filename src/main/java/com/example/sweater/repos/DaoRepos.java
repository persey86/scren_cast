package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;
import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DaoRepos extends CrudRepository<Message, Long> {

	List<DtoJoke> getJokesByCount(int count);
}
