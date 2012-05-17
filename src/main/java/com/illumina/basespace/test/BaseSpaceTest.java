package com.illumina.basespace.test;

import java.util.List;

import com.illumina.basespace.BaseSpaceSession;
import com.illumina.basespace.BaseSpaceSessionEvent;
import com.illumina.basespace.BaseSpaceSessionListener;
import com.illumina.basespace.BaseSpaceSessionManager;
import com.illumina.basespace.FetchParams;
import com.illumina.basespace.Project;
import com.illumina.basespace.User;


public class BaseSpaceTest
{
    public static void main(final String args[])
    {
        try
        {
            if (args.length != 2)
            {
                System.out.println("Usage: BaseSpaceTest [clientId] [clientSecret]");
                System.exit(0);
            }
            
            TestBaseSpaceConfiguration config = new TestBaseSpaceConfiguration()
            {
                @Override
                public String getClientId()
                {
                    return args[0];
                }
                @Override
                public String getClientSecret()
                {
                    return args[1];
                }
            };            
            
            BaseSpaceSessionManager.instance().addSessionListener(new BaseSpaceSessionListener()
            {
                @Override
                public void sessionEstablished(BaseSpaceSessionEvent evt)
                {
                    BaseSpaceSession session = evt.getSession();
                    User user = session.getCurrentUser();
                    System.out.println("Current user is " + user.getName() + " id# " + user.getId()  + "[" + user.getEmail() + "]");
                    FetchParams params = new FetchParams(1); //Fetch 1 project
                    List<Project>projects = session.getProjects(user, params);
                    for(Project project:projects)
                    {
                        System.out.println("Project " + project.getId() + " owned by " + project.getUserOwnedBy().getName());
                    }
                }
            });
            BaseSpaceSessionManager.instance().requestSession("test",config);
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }
    
    
}
