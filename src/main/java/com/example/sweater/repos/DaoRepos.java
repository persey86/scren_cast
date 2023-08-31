package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;

import java.util.List;

public interface DaoRepos {

	List<DtoJoke> getJokesByCount(int count);
}
