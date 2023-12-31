package com.example.sweater;

import com.example.sweater.domain.DtoJoke;
import com.example.sweater.repos.DaoReposImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JokeController {

	private DaoReposImpl daoRepos;


	@GetMapping("/jokes/{count}")
	public ResponseEntity<DtoJoke> one(@PathVariable int count) {
		List<DtoJoke> result = daoRepos.getJokesByCount(count);

		return (result != null) ?
				new ResponseEntity<>((DtoJoke) result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}


}
