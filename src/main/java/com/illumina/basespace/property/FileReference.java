package com.illumina.basespace.property;

import com.illumina.basespace.entity.FileCompact;
import com.illumina.basespace.util.TypeHelper;

public class FileReference extends ReferenceProperty<FileCompact>
{

    @Override
    public String getType()
    {
        return this.getContent() != null?TypeHelper.PROPERTY_FILE:TypeHelper.PROPERTY_FILE_ARRAY;
    }

}
