import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main12341 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        do {
            lines.add(scan.nextLine());
        } while (!lines.get(lines.size() - 1).contains("</root>"));
        StringBuilder sb = new StringBuilder();
        lines.remove(0);
        lines.remove(0);
        lines.remove(lines.size() - 1);
        Deque<Integer> parent = new LinkedList<>();
        parent.add(0);
        Pattern idPattern = Pattern.compile("id=\"?(\\d+)");
        Pattern valuePattern = Pattern.compile("value=\"?(.+?)\"?/");
        for (String line : lines) {
            if (line.trim().startsWith("</")) {
                parent.removeLast();
            } else {
                Matcher idMatcher = idPattern.matcher(line);
                idMatcher.find();
                String idString = idMatcher.group(1);
                int parentId = parent.getLast();
                int id = Integer.parseInt(idString);
                if (!line.trim().endsWith("/>")) {
                    parent.addLast(id);
                }
                Matcher valueMatcher = valuePattern.matcher(line);
                String valueString = "";
                if (valueMatcher.find()) {
                    valueString = " " + valueMatcher.group(1);
                }
                System.out.println(parentId + " " + id + valueString);
            }
        }
    }
}