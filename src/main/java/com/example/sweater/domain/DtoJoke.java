package com.example.sweater.domain;

import com.example.sweater.repos.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_JOKES")
public class DtoJoke {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String setup;

	private Type type;

	private String punchline;


	public DtoJoke() {
	}

	public DtoJoke(String setup, Type type, String punchline) {
		this.setup = setup;
		this.type = type;
		this.punchline = punchline;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSetup() {
		return setup;
	}

	public void setSetup(String setup) {
		this.setup = setup;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getPunchline() {
		return punchline;
	}

	public void setPunchline(String punchline) {
		this.punchline = punchline;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DtoJoke dtoJoke = (DtoJoke) o;
		return id.equals(dtoJoke.id) && Objects.equals(setup, dtoJoke.setup) && type == dtoJoke.type && Objects
				.equals(punchline, dtoJoke.punchline);
	}

	@Override public int hashCode() {
		return Objects.hash(id, setup, type, punchline);
	}

	@Override public String toString() {
		return "{" + "id=" + id + ", setup='" + setup + '\'' + ", type=" + type + ", punchline='" + punchline + '\'' + '}';
	}
}
