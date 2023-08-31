package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;

import java.util.List;

public interface DaoService extends DaoRepos {

	List<DtoJoke> findByType(Type type);

	List<DtoJoke> findTop100ById();

}
