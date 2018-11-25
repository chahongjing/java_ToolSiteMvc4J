package com.zjy.entities;

import com.zjy.entities.enums.Sex;
import com.zjy.entities.enums.YesNo;

import java.util.Date;

/**
 * @author chahongjing
 * @create 2016-12-05 22:07
 */
public class UserInfo {
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
//    @TableId
    private String userId;
    /**
     * 代码
     */
//    @JSONField(name="user_code")
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
    private Sex sex;
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
    private YesNo isDisabled;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 权限
     */
    private String permissionGuid;

    /**
     * 是否系统级
     */
    private YesNo isSystem;

    //    @TableField(strategy = FieldStrategy.NOT_EMPTY)
    private int age;

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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
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

    public YesNo getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(YesNo disabled) {
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

    public YesNo getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(YesNo system) {
        isSystem = system;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
