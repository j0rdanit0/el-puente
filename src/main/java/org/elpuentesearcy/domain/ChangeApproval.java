package org.elpuentesearcy.domain;

import java.util.ArrayList;
import java.util.List;

public class ChangeApproval
{
    private String changeText;
    private List<String> approvals = new ArrayList<>();
    private List<String> denials = new ArrayList<>();
    private List<String> denialReasons = new ArrayList<>();

    public ChangeApproval()
    {
    }

    public ChangeApproval( String changeText )
    {
        this.changeText = changeText;
    }

    public String getChangeText()
    {
        return changeText;
    }

    public void setChangeText( String changeText )
    {
        this.changeText = changeText;
    }

    public List<String> getApprovals()
    {
        return approvals;
    }

    public void setApprovals( List<String> approvals )
    {
        this.approvals = approvals;
    }

    public List<String> getDenials()
    {
        return denials;
    }

    public void setDenials( List<String> denials )
    {
        this.denials = denials;
    }

    public List<String> getDenialReasons()
    {
        return denialReasons;
    }

    public void setDenialReasons( List<String> denialReasons )
    {
        this.denialReasons = denialReasons;
    }
}
