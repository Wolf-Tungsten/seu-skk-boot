package skk.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="bvo")
public class Bvo {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "userid",length = 64)
    public String userid;

    @Column(name="name")
    public String name;

    @Column(name = "email")
    public String email;

    @Column(name = "phone")
    public String phone;


}
