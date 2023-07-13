package macauyeah.personal.springbootdatajpa.entitythree.database.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;

import org.springframework.util.StringUtils;

@Entity
public class VersionControl {
    @Id
    private String id;
    private String remark;
    @Version
    @Column(nullable = false)
    private long version = 0L;

    @PrePersist
    public void genUUID() {
        Date now = new Date();
        if (!StringUtils.hasLength(this.getId())) {
            this.setId(now.getTime() + "_" + UUID.randomUUID().toString());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
