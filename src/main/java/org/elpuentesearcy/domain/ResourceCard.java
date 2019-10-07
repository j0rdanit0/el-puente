package org.elpuentesearcy.domain;

public class ResourceCard implements Comparable<ResourceCard>
{
    private String label;
    private String url;

    public ResourceCard( String label, String url )
    {
        this.label = label;
        this.url = url;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    @Override
    public int compareTo( ResourceCard that )
    {
        return this.getLabel().toUpperCase().compareTo( that.getLabel().toUpperCase() );
    }
}
