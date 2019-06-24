package org.elpuentesearcy.domain;

public class ChangeApprovalRequest
{
    private boolean undo;
    private int changeIndex;
    private boolean approved;
    private String author;
    private String denialReason;

    public ChangeApprovalRequest()
    {
    }

    public boolean isUndo()
    {
        return undo;
    }

    public void setUndo( boolean undo )
    {
        this.undo = undo;
    }

    public int getChangeIndex()
    {
        return changeIndex;
    }

    public void setChangeIndex( int changeIndex )
    {
        this.changeIndex = changeIndex;
    }

    public boolean isApproved()
    {
        return approved;
    }

    public void setApproved( boolean approved )
    {
        this.approved = approved;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    public String getDenialReason()
    {
        return denialReason;
    }

    public void setDenialReason( String denialReason )
    {
        this.denialReason = denialReason;
    }
}
