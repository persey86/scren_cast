package com.example.sweater.repos;

import com.example.sweater.domain.DtoJoke;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoReposImpl implements DaoRepos {

	@Autowired
	private DaoService daoService;
	private SessionFactory sessionFactory;

	@Override
	public List<DtoJoke> getJokesByCount(int count) {

		List<DtoJoke> result;
// TODO get count of pages
		if (count == 1) {
			DtoJoke one = (DtoJoke) daoService.getOne((long) count);
			result = new ArrayList<>();
			result.add(one);
		} else {
			Session session = sessionFactory.getCurrentSession();

			Long countRez = (Long) session
					.createQuery("count (j.id) from DtoJoke j").uniqueResult();

			int lastPage = (int) (Math.ceil(countRez/10));

			Query<DtoJoke> query = session
					.createQuery("From DtoJoke j ORDER BY j.id", DtoJoke.class)
					.setFirstResult((lastPage - 1) * 10)
					.setMaxResults(10);

			result = query.getResultList();
		}

		return result;
	}
}
