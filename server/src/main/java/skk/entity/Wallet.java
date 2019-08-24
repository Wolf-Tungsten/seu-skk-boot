package skk.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="wallet")
public class Wallet {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    //此处userid可能为品牌方或者借卖方userid
    @Column(name="userid",nullable = false,length = 64)
    public String userid;

    @Column(name = "accountName",length = 64)
    public String accountName;

    @Column(name = "email",length = 64)
    public String email;

    @Column(name = "password",length = 64)
    public String password;

    //账户余额
    @Column(name = "balance")
    public Integer balance = 100000;

}
