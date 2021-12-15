package com.ponto.api.entity.dto.security;

public class UsuarioLoginDTO {

	private Integer id;
	private String email;

	private String token;

	public UsuarioLoginDTO(String token, String email) {
		this.token = token;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
