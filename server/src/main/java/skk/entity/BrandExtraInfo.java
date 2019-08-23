package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="brandExtInfo")
public class BrandExtraInfo {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "mvoId", nullable = false, length = 64)
    public String mvoId;

    @Column(name = "companyNameEnglish", nullable = false, length = 64)
    public String companyNameEnglish;

    @Column(name = "companyNameChinese", nullable = false, length = 64)
    public String companyNameChinese;

    @Column(name = "brief", nullable = false, length = 255)
    public String brief;

    @Column(name = "gcmReportType", nullable = false, length = 64)
    public String gcmReportType;

    @Column(name = "gcmReportUrl", nullable = false, length = 64)
    public String gcmReportUrl;
}
