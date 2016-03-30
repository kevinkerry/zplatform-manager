package com.zlebank.zplatform.manager.dao.object.scan;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * t_channel_file entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CHANNEL_FILE")
public class ChannelFileMode implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    //渠道标识
    private String chnlId;
    //渠道代码
    private String chnlCode;
    //渠道名称
    private String chnlName;
    //文件名
    private String fileName;
    //渠道状态 00父渠道 01子渠道
    private String status;
    //类的路径
    private String classPath;
    
    
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name = "CHANNEL_ID", unique = true, nullable = false, precision = 10, scale = 0)
    public String getChnlId() {
        return chnlId;
    }

    public void setChnlId(String chnlId) {
        this.chnlId = chnlId;
    }
    @Column(name = "CHANNEL_CODE")
    public String getChnlCode() {
        return chnlCode;
    }
    public void setChnlCode(String chnlCode) {
        this.chnlCode = chnlCode;
    }
    @Column(name = "CHANNEL_NAME")
    public String getChnlName() {
        return chnlName;
    }
    public void setChnlName(String chnlName) {
        this.chnlName = chnlName;
    }
    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @Column(name = "status")    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "ClASS_PATH")    
    public String getClassPath() {
        return classPath;
    }
    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
    
    
    
}