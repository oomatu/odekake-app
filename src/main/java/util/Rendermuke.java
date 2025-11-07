/*package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    // TNS_ADMINの値を環境変数から取得します。
    // DockerfileのENTRYPOINTでは、-DTNS_ADMIN=/app/wallet を設定しているため、
    // Oracle JDBCドライバは自動的にそのパスを参照します。
    
    private static final String BASE_URL = "jdbc:oracle:thin:@odekakedb_medium";
    private static final String USERNAME = "ADMIN";
    // NOTE: パスワードも環境変数から取得することが、セキュリティ上は強く推奨されます
    private static final String PASSWORD = "koreGApassw0rd"; 

    // 環境変数 TNS_ADMIN_OVERRIDE_PATH が設定されていれば、その値を優先して使用します。
    // この環境変数は、ローカル環境でのテスト用にパスを一時的に上書きするために使用します。
    private static final String TNS_ADMIN_OVERRIDE = System.getenv("TNS_ADMIN_OVERRIDE_PATH");

    public static Connection getConnection() throws Exception {
        
        String url;
        
        // 1. 環境変数 TNS_ADMIN_OVERRIDE_PATH が設定されている場合 (主にローカル環境)
        //    JDBC URLにTNS_ADMINパラメータを直接含めて、パスをオーバーライドします。
        if (TNS_ADMIN_OVERRIDE != null && !TNS_ADMIN_OVERRIDE.isEmpty()) {
            // 例: TNS_ADMIN_OVERRIDE_PATH=D:/.../wallet を設定した場合
            url = BASE_URL + "?TNS_ADMIN=" + TNS_ADMIN_OVERRIDE;
            System.out.println("Using TNS_ADMIN override URL: " + url); // ログ出力
        } 
        // 2. 環境変数が設定されていない場合 (主にRender/Docker環境)
        //    DockerfileのENTRYPOINTで設定した -DTNS_ADMIN が自動的に適用されるため、
        //    シンプルなBASE_URLを使用します。
        else {
            url = BASE_URL;
            System.out.println("Using simple TNS alias URL: " + url); // ログ出力
        }

        return DriverManager.getConnection(url, USERNAME, PASSWORD);
    }
}*/