package org.elpuentesearcy.domain;

import lombok.Data;

@Data
public class ResourceCard implements Comparable<ResourceCard>
{
    private final String label;
    private final String url;
    private String icon;
    private String colorClass;

    public ResourceCard( String label, String url )
    {
        this.label = label;
        this.url = url;
        this.icon = "fas fa-link";
        this.colorClass = "card-blue";
    }

    public ResourceCard( String label, String url, String icon, String colorClass )
    {
        this.label = label;
        this.url = url;
        this.icon = icon;
        this.colorClass = colorClass;
    }

    @Override
    public int compareTo( ResourceCard that )
    {
        return this.getLabel().toUpperCase().compareTo( that.getLabel().toUpperCase() );
    }
}
