package kadai_007;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {
	
	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			// (1)データベースに接続する
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					""
			);
			
			System.out.println("データベース接続成功：" + con);
			
			// (2)SQLクエリを準備する
			statement = con.createStatement();
			
			// (3)投稿データを追加する
			String sql1 = """
						INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES
						(1003, "2023-02-08", "昨日の夜は徹夜でした・・", 13),
						(1002, "2023-02-08", "お疲れ様です！", 12),
						(1003, "2023-02-08", "今日も頑張ります！", 18),
						(1001, "2023-02-09", "無理は禁物ですよ！", 17),
						(1002, "2023-02-10", "明日から連休ですね！", 20);
						""";
			// (4)投稿データを検索する
			String sql2 = """
						SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002;
					""";
			// SQLクエリを実行
			System.out.println("レコード追加を実行します");
			int rowCnt1 = statement.executeUpdate(sql1);
			System.out.println(rowCnt1 + "件のレコードが追加されました");
			ResultSet result = statement.executeQuery(sql2);
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			// (5)投稿データを抽出、表示する
            while(result.next()) {
                Date posted_at = result.getDate("posted_at");
                String post_content= result.getString("post_content");
                int likes = result.getInt("likes");
                System.out.println(result.getRow() + "件目：投稿日時=" + posted_at + "／投稿内容=" +  post_content + "／いいね数=" + likes);
            }
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			//使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {}
			}
		}
		
	}

}
