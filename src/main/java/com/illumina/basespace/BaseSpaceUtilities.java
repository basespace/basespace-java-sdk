package com.illumina.basespace;

import java.lang.annotation.Annotation;

/**
 * 
 * @author bking
 *
 */
class BaseSpaceUtilities
{
    static void assertNotNull(Object object,String msg)
    {
        if (object == null)throw new RuntimeException("Unallowed null: " + msg);
    }
    
    @SuppressWarnings("unchecked")
    static <T extends Annotation> T getAnnotation(Class<T>annotationClass,Class<?>targetClass)
    {
        Annotation path = targetClass.getAnnotation(annotationClass);
        assertNotNull(path,targetClass.getName() + " missing required annotation " + annotationClass.getName());
        return (T) path;
    }
}
