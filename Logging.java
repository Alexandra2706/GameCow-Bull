import java.util.ArrayList;
import java.util.List;

public class Logging {
    private List<String> logList;

    public Logging() {
        this.logList = new ArrayList<>();
    }

    public void addResult(String str) {
        logList.add(str);
    }

    public List<String> getLogList() {
        return logList;
    }

}

// System.out.println(nGame.getLogging().stream().collect(Collectors.joining("\n")));