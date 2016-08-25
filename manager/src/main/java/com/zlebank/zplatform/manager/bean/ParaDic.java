package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

public class ParaDic implements Bean{

    private int tid;
    private String paraCode;
    private int parentId;
    private String paraname;
    private String paraType;
    private int hasSub;
    private int status;
    private int added;
    private String remarks;
    
    
    
    public ParaDic(int tid, String paraCode, int parentId, String paraname,
            String paraType, int hasSub, int status, int added, String remarks) {
        super();
        this.tid = tid;
        this.paraCode = paraCode;
        this.parentId = parentId;
        this.paraname = paraname;
        this.paraType = paraType;
        this.hasSub = hasSub;
        this.status = status;
        this.added = added;
        this.remarks = remarks;
    }
    public ParaDic() {
        super();
    }
    public int getTid() {
        return tid;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }
    public String getParaCode() {
        return paraCode;
    }
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
  
    public String getParaname() {
        return paraname;
    }
    public void setParaname(String paraname) {
        this.paraname = paraname;
    }
    public String getParaType() {
        return paraType;
    }
    public void setParaType(String paraType) {
        this.paraType = paraType;
    }
    public int getHasSub() {
        return hasSub;
    }
    public void setHasSub(int hasSub) {
        this.hasSub = hasSub;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getAdded() {
        return added;
    }
    public void setAdded(int added) {
        this.added = added;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
