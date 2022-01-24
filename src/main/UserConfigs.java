package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserConfigs {
    private String siteName;
    private String siteUrl;
    private String table;
    private String authorId;
    private String dbName;
    private String username;
    private String password;

    public UserConfigs() {
        getConfigs();
    }

    private void getConfigs(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("post_conf.txt"));
            String s;
            Map<String,String> configs = new HashMap();
            while((s = reader.readLine()) != null){
                String[] d = s.split("=");
                configs.put(d[0],d[1]);
            }
            for(String k: configs.keySet()){
                switch (k) {
                    case "siteName": siteName = configs.get(k); break;
                    case "siteUrl": siteUrl = configs.get(k); break;
                    case "table": table = configs.get(k); break;
                    case "authorId": authorId = configs.get(k); break;
                    case "dbName": dbName = configs.get(k); break;
                    case "username": username = configs.get(k); break;
                    case "password": password = configs.get(k); break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public String getTable() {
        return table;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
