package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class RestHelper {
    private ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public String getIssueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return issues.getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsJsonPrimitive().getAsString();
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
