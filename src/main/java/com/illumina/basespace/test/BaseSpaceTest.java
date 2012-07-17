/**
* Copyright 2012 Illumina
* 
 * Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*    http://www.apache.org/licenses/LICENSE-2.0
* 
 *  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

package com.illumina.basespace.test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.illumina.basespace.Analysis;
import com.illumina.basespace.BaseSpaceSession;
import com.illumina.basespace.BaseSpaceSessionManager;
import com.illumina.basespace.CoverageMetaData;
import com.illumina.basespace.CoverageRecord;
import com.illumina.basespace.ExtendedFileInfo;
import com.illumina.basespace.FetchParams;
import com.illumina.basespace.File;
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
            
            
            BaseSpaceSession session = BaseSpaceSessionManager.instance().requestSession(config);
            

            User user = session.getCurrentUser();
            System.out.println("Current user is " + user.getName() + " id# " + user.getId()  + "[" + user.getEmail() + "]");
            FetchParams params = new FetchParams(1); //Fetch 1 project
            List<Project>projects = session.getProjects(user, params);
                    
                
            
                for(Project project:projects)
                {
                    System.out.println("Project " + project.getId() + " owned by " + project.getUserOwnedBy().getName());
                
                    for(Analysis analysis:session.getAnalyses(project, params))
                    {
                        //System.out.println("Analysis: " + analysis.toString());
                        
                        
                        for(File file:session.getFiles(analysis, null))
                        {
                            System.out.println("File: " + file.toString());
                            
                            if (file.getName().endsWith("bam"))
                            {
                                ExtendedFileInfo extInfo = session.getFileExtendedInfo(file.getId(), ExtendedFileInfo.class);
                                
                               /*
                                CoverageRecord coverage = session.getCoverage(extInfo, "chr1", 137931431, 
                                        240658294, 1);
                               */
                                
                                CoverageMetaData metaData = session.getCoverageMetaData(extInfo,  "chr1");
                                
                                /*
                                CoverageRecord coverage = session.getCoverage(extInfo, "chr1", 67108864, 
                                        67110912, 0);
                                */
                                
                                CoverageRecord coverage = session.getCoverage(extInfo, "chr1", 121485425, 
                                        121490000 , 12);
                                
                                System.out.println(coverage.toString());
                                
                                
                                break;
                                
                            }
                            
                            if (file.getName().endsWith("vcf"))
                            {
                                /*
                                session.addDownloadListener(new DownloadListener()
                                {

                                    @Override
                                    public void progress(DownloadEvent evt)
                                    {
                                        System.out.println(evt.getCurrentBytes());
                                        
                                    }

                                    @Override
                                    public void complete(DownloadEvent evt)
                                    {
                                        // TODO Auto-generated method stub
                                        
                                    }

                                    @Override
                                    public void canceled(DownloadEvent evt)
                                    {
                                        // TODO Auto-generated method stub
                                        
                                    }
                                    
                                });
                                
                                session.download(file, new java.io.File("C:\\theFile.vcf"));
                                */
                            }
                            
                            /*
                            if (file.getName().endsWith("vcf"))
                            {
                                VariantFile vFile = session.getFileExtendedInfo(file, VariantFile.class);
                                System.out.println("Variant File: " + vFile.toString());
                                
                                /* 
                                List<VariantRecord>records = session.queryJSON(vFile,"chr2",1,99999999); 
                                for(VariantRecord record:records)
                                {
                                    System.out.println(record.toString());
                                    
                                }*/
                                
                                //String raw = session.queryRaw(vFile,"chr2",1,99999999);
                                //getRecords(raw);
                            }
                           
                          
                        }
                    }
                    
                    /*
                    for(Sample sample:session.getSamples(project, params))
                    {
                        System.out.println("Sample: " + sample.toString());
                        
                    }
                    */
               
                    
                    /*
                    for(Run run:session.getRuns(user, params))
                    {
                        System.out.println("Run " + run.getExperimentName());
                        
                        for(Sample sample:session.getSamples(run, params))
                        {
                            System.out.println("Sample: " + sample.toString());
                            
                        }
                        
                        for(File file:session.getFiles(run, null))
                        {
                            System.out.println("File " + run.toString());
                        }
                        
                    }
                    */
    
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }
    
    public static List<String>getRecords(String raw)
    {
        List<String>rtn = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(raw,"\r\n");        
        while(st.hasMoreTokens())
        {
            String next = st.nextToken();
            System.out.println("line: " + next);
        }
        
        return rtn;
        
    }
    
}
