package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="Gvo")
public class Gvo {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "userId", unique = true, nullable = false, length = 64)
    public String userId;
}
