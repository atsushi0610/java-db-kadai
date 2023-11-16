package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

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
            System.out.println("データベース接続成功");

         // (2)SQLクエリを準備する
            statement = con.createStatement();
            String sql1 = "UPDATE scores SET score_math = 95, score_english = 80  WHERE id = 5;";
            String sql2 = "SELECT id, name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC";

         // (3)点数データを更新する
            System.out.println("レコード更新を実行します");
            int rowCnt = statement.executeUpdate(sql1);
            System.out.println(rowCnt + "件のレコードが更新されました");
            
         // (4)点数データを更新する
            System.out.println("数学・英語の点数が高い順に並べ替えました");
            
         // (5)並べ替え結果を表示する
            ResultSet result = statement.executeQuery(sql2);
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                int score_math = result.getInt("score_math");
                int score_english = result.getInt("score_english");
                System.out.println(result.getRow() + "件目：生徒ID=" + id
                                   + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english );
            }
        } catch(SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 使用したオブジェクトを解放
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
        }

	}

}
