package com.zjy.entities;

import java.util.Date;

//import javax.persistence.*;

/**
 * @author chahongjing
 * @create 2016-12-05 22:07
 */
//@Entity
//@Table(name = "UserInfo")
public class UserInfoForHibernate {
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建日期
     */
    private Date createdOn;
    /**
     * 修改人
     */
    private String modifiedBy;
    /**
     * 修改日期
     */
    private Date modifiedOn;
    /**
     * 用户Guid
     */
    //@Id//声明此列为主键
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;
    /**
     * 代码
     */
    private String userCode;
    /**
     * 名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private boolean sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 头像
     */
    private String photo;
    /**
     * 公司Guid
     */
    private String companyGuid;
    /**
     * 部门Guid
     */
    private String departmentGuid;
    /**
     * 是否禁用
     */
    private boolean isDisabled;

    /**
     * 公司名称
     */
    //@Transient
    private String companyName;

    /**
     * 部门名称
     */
    //@Transient
    private String departmentName;

    /**
     * 权限
     */
    //@Transient
    private String permissionGuid;

    /**
     * 是否系统级
     */
    private boolean isSystem;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid;
    }

    public String getDepartmentGuid() {
        return departmentGuid;
    }

    public void setDepartmentGuid(String departmentGuid) {
        this.departmentGuid = departmentGuid;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPermissionGuid() {
        return permissionGuid;
    }

    public void setPermissionGuid(String permissionGuid) {
        this.permissionGuid = permissionGuid;
    }

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean system) {
        isSystem = system;
    }
}
