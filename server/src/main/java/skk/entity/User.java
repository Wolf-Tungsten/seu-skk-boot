package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="user")
public class User {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "username", unique = true, nullable = false, length = 64)
    public String username;

    @Column(name = "password", nullable = false, length = 64)
    public String password;

    @Column(name = "role", length = 10, nullable = false)
    public String role;

    @Column(name = "token", length = 255, nullable = true)
    public String token;
}
