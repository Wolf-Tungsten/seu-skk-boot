package skk;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
	@GenericGenerator(name="user_id",strategy = "uuid")
    @GeneratedValue(generator = "user_id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}

