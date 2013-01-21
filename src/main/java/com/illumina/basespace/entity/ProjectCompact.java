package com.illumina.basespace.entity;

import com.illumina.basespace.util.ValidationHelper;
import com.sun.jersey.api.representation.Form;

public class ProjectCompact extends UserOwnedResource
{

    public ProjectCompact()
    {
        super();
    }

    public ProjectCompact(String id)
    {
        super(id);
    }
    
    public void setName(String name)
    {
        super.setName(name);
    }
    
    @Override
    public Form toForm()
    {
        ValidationHelper.assertNotNull(getName(), "Project name");
        Form rtn = new Form();
        rtn.add("name",getName());
        return rtn;
    }
    
}
