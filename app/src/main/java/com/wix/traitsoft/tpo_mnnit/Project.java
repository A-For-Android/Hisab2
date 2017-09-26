package com.wix.traitsoft.tpo_mnnit;

/**
 * Created by shiva on 25-09-2017.
 */

public class Project extends Profile {

    private int noOfProject;
    private int noOfIntern;
    private String projectDesription[];
    private String internDesription[];

    Project(int noOfProject,int noOfIntern)
    {
        this.noOfProject = noOfProject;
        this.noOfIntern = noOfIntern;
        projectDesription = new String[noOfProject];
        internDesription = new String[noOfIntern];
    }

    public void completeProfile()
    {

    }

    public void dispalyProfile()
    {

    }

    public boolean IsCompleted()
    {
        return true;
    }
}
