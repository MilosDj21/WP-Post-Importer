package main;

import java.io.Serializable;

public class PostData implements Serializable {
    private String status;
    private String keyword;

    public PostData(String status, String keyword) {
        this.status = status;
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public String getKeyword() {
        return keyword;
    }
}
