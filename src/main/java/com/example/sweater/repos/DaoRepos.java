package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DaoRepos {

	List<DtoJoke> getJokesByCount(int count);
}
