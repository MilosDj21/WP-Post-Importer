package connection;

import main.UserConfigs;
import post_create.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PostService {
    private static Post getOne(Post post, UserConfigs configs) throws SQLException {
        Connection conn = new DBConnection(configs.getDbName(), configs.getUsername(), configs.getPassword()).getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + configs.getTable() + " WHERE post_name = ?");
        ps.setString(1, post.getPostName());
        ResultSet rs = ps.executeQuery();
        Post p = null;
        if(rs.next()){
            p = new Post(
                    rs.getString(5),
                    configs.getSiteName(),
                    configs.getSiteUrl(),
                    rs.getString(7)
            );
        }
        ps.close();
        rs.close();
        conn.close();
        return p;
    }

    public static void savePost(Post post, UserConfigs configs) throws SQLException {
        int counter = 0;
        String s = post.getPostName();
        while(getOne(post,configs) != null){
            post.setPostName(s + "-" + ++counter);
        }
        Connection conn = new DBConnection(configs.getDbName(), configs.getUsername(),configs.getPassword()).getConnection();
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PreparedStatement ps = conn.prepareStatement("INSERT INTO " + configs.getTable() + " (post_author, post_date, post_date_gmt, post_content, post_title, post_excerpt, post_status, to_ping, pinged, post_content_filtered, post_name, post_modified, post_modified_gmt, guid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, configs.getAuthorId());
        ps.setString(2, sdf.format(time));
        ps.setString(3, sdf.format(time));
        ps.setString(4, post.getContent());
        ps.setString(5, post.getKeyword());
        ps.setString(6, "");
        ps.setString(7, post.getStatus());
        ps.setString(8, "");
        ps.setString(9, "");
        ps.setString(10, "");
        ps.setString(11, post.getPostName());
        ps.setString(12, sdf.format(time));
        ps.setString(13, sdf.format(time));
        ps.setString(14, "https://" + configs.getSiteUrl() + "/" + post.getPostName());

        ps.executeUpdate();

    }
}
