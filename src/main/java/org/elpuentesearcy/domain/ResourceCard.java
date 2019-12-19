package org.elpuentesearcy.domain;

import lombok.Data;

@Data
public class ResourceCard implements Comparable<ResourceCard>
{
    private final String label;
    private final String url;

    @Override
    public int compareTo( ResourceCard that )
    {
        return this.getLabel().toUpperCase().compareTo( that.getLabel().toUpperCase() );
    }
}
