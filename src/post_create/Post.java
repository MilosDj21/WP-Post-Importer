package post_create;

import java.io.*;
import java.time.LocalDate;

public class Post {
    private String keyword;
    private String siteName;
    private String siteurl;
    private String content;
    private String status;
    private String postName;

    public Post(String keyword, String siteName, String siteurl, String status) {
        this.keyword = keyword;
        this.siteName = siteName;
        this.siteurl = siteurl;
        this.status = status;
        this.postName = "best-" + keyword.toLowerCase().replaceAll(" ", "-");

        getText();
    }

    private void getText(){
        String text = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("post_tekst.txt"));
            String s;
            while((s = reader.readLine()) != null){
                text += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        alterText(text);
    }

    private void alterText(String text){
        text = text.replaceAll("VARIABLE", keyword);
        text = text.replaceAll("SITENAME", siteName);
        text = text.replaceAll("SITEURL", siteurl);

        content = text;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteurl() {
        return siteurl;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
