package oliveira.gabriel.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Entity;

@Entity
public class User extends PanacheEntity {

	public String name;
	public String documentId;
	public String email;
	public String password;
	
}
