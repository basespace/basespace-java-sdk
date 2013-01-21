package com.illumina.basespace.util;

public class ValidationHelper
{
    public static void assertNotNull(Object val,String msgIfNull)
    {
        if (val == null)throw new IllegalArgumentException("null " + msgIfNull);
    }
}
