package com.allen.model;

import java.util.Date;

public class District {
    private Integer id;

    private String districtname;

    private Date createtime;

    private Short isinvalid;

    public District(Integer id, String districtname, Date createtime, Short isinvalid) {
        this.id = id;
        this.districtname = districtname;
        this.createtime = createtime;
        this.isinvalid = isinvalid;
    }

    public District() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname == null ? null : districtname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Short getIsinvalid() {
        return isinvalid;
    }

    public void setIsinvalid(Short isinvalid) {
        this.isinvalid = isinvalid;
    }
}