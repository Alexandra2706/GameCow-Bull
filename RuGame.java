import java.util.ArrayList;
import java.util.List;

public class RuGame extends AbstractGame {

    @Override
    public List<String> generateCharList() {
        List<String> result = new ArrayList<>();
        for (int i = 'а'; i <= 'я'; i++) {
            result.add(String.valueOf((char) i));
        }
        result.add("ё");
        return result;
    }
}
