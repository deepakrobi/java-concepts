package main.java;

import com.google.common.net.InternetDomainName;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DomainExtractor {

    void extractDomainNanmes() throws IOException {
        Map domainMap = new HashMap();
        File f1=new File("/Users/dkumar/Downloads/BratReferrer.txt");
        FileReader fr=new FileReader(f1);
        BufferedReader br=new BufferedReader(fr);

        String s = br.readLine();

        // Writing data
        File f2=new File("/Users/dkumar/Downloads/BRATDomains.txt");
        FileWriter fw=new FileWriter(f2);
        BufferedWriter bw=new BufferedWriter(fw);
        while(s!=null)
        {

            //System.out.println(InternetDomainName.from(s).topPrivateDomain().toString());
            String domainName =  s.replaceAll("http(s)?://|www\\.|/.*", "");

            if(domainMap.get(domainName)== null){
                domainMap.put(domainName,domainName);
                bw.newLine();
                bw.write(domainName);
                System.out.println(domainName);
            }else{
                System.out.println( "Already Existing dommain *******  "+domainName);
            }
            s=br.readLine();

        }
        bw.flush();
        bw.close();
    }
    public static void main(String[] args) {
    DomainExtractor domainExtractor = new DomainExtractor();
        try {
            domainExtractor.extractDomainNanmes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
