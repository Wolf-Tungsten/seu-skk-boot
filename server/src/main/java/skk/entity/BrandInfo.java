package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="brandInfo")
public class BrandInfo {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "mvoId", nullable = false, length = 64)
    public String mvoId;

    @Column(name = "brandName", nullable = false, length = 64)
    public String brandName;

    @Column(name = "brandLogo", nullable = false, length = 1024000)
    public String brandLogo;
}
