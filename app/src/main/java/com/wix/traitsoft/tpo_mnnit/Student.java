package com.wix.traitsoft.tpo_mnnit;

/**
 * Created by shiva on 25-09-2017.
 */

public abstract class Student {

    private String username;
    private String passwd;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPasswd(String passwd)
    {
        this.passwd = passwd;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPasswd()
    {
        return passwd;
    }

    public abstract void completeProfile();

}
