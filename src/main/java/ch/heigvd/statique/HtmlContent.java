package ch.heigvd.statique;

public class HtmlContent {
    private final StringBuilder content;

    public HtmlContent() {
        this.content = new StringBuilder();
    }

    public StringBuilder getContent() {
        return content;
    }

    public void append(String str) {
        content.append(str);
    }
}
