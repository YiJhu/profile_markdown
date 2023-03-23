import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import org.jetbrains.annotations.NotNull;
import org.json.*;

public class GithubProfileReadme {
    public static void main(String[] args) throws Exception {
        String username = "YiJhu"; // 在這裡替換您的 GitHub 用戶名
        String apiUrl = "https://api.github.com/users/" + username;

        // 通過 GitHub API 獲取帳號訊息
        String data = readUrl(apiUrl);
        JSONObject json = new JSONObject(data);
        String name = json.getString("name");
        String bio = json.getString("bio");
        int followers = json.getInt("followers");
        int following = json.getInt("following");
        int publicRepos = json.getInt("public_repos");

        // 創建 Markdown 格式的 README 文件內容
        String markdown = "# " + name + "\n\n";
        markdown += "## " + bio + "\n\n";
        markdown += "- " + followers + " followers\n";
        markdown += "- " + following + " following\n";
        markdown += "- " + publicRepos + " public repositories\n\n";
        markdown += "## Latest projects\n\n";
        markdown += "- [Project 1](https://github.com/user/project-1)\n";
        markdown += "- [Project 2](https://github.com/user/project-2)\n";
        markdown += "- [Project 3](https://github.com/user/project-3)\n\n";
        markdown += "## Github stats\n\n";
        markdown += "<img src=\"https://github-readme-stats.vercel.app/api?username=" + username + "&show_icons=true&theme=radical\" alt=\"GitHub stats\"/>\n";

        // 將 Markdown 內容寫入 README 文件
        File file = new File("README.md");
        FileWriter writer = new FileWriter(file);
        writer.write(markdown);
        writer.close();
    }

    // 從 URL 中讀取數據
    private static @NotNull String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
