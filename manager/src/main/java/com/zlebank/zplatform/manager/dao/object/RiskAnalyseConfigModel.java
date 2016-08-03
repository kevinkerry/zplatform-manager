package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_risk_analyse_config")
public class RiskAnalyseConfigModel implements Serializable {

    /**规则编号**/
    private String rolecode; 
    /**规则描述**/
    private String roledesc;
    
    
    public RiskAnalyseConfigModel() {
        super();
    }


    public RiskAnalyseConfigModel(String rolecode, String roledesc) {
        super();
        this.rolecode = rolecode;
        this.roledesc = roledesc;
    }


    @Column(name="ROLECODE",length=8)
    public String getRolecode() {
        return rolecode;
    }


    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }


    @Column(name="ROLEDESC",length=64)
    public String getRoledesc() {
        return roledesc;
    }


    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }
    
    
}
