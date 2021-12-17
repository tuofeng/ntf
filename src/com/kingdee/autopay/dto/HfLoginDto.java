package com.kingdee.autopay.dto;

/**
 * Created with IntelliJ IDEA
 *
 * @author: shuangfeng_li
 * @date: 2019/3/12 19:07
 */
public class HfLoginDto
        extends HfParameterEncode {

    /**
     * ’À∫≈
     */
    private String empAccount;

    /**
     * √‹¬Î
     */
    private String empPass;

    public String getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(String empAccount) {
        this.empAccount = empAccount;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    @Override
    protected String getUrl() {
        return "login";
    }
}
