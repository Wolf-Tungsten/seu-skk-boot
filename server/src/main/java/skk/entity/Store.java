package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="store")
public class Store {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    //sellerid即借卖方的uuid
    @Column(name = "sellerid",unique = true, nullable = false, length = 64)
    public String sellerid;

    @Column(name = "storename",unique = true, nullable = false, length = 64)
    public String storename;

    @Column(name = "marketplaceId",length = 64)
    public String marketplaceId;

    @Column(name = "token",length = 64)
    public String token;



}
