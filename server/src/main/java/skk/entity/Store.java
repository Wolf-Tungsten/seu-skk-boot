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
    @Column(name = "sellerid", nullable = false, length = 64)
    public String sellerid;

    @Column(name = "storename", nullable = false, length = 64)
    public String storename;

    @Column(name = "marketplaceId",length = 64)
    public String marketplaceId;

    @Column(name = "storetoken",length = 64)
    public String storetoken;

    //店铺类型，是Amazon还是EBay，用String
    @Column(name = "type",nullable = false)
    public String type;



}
