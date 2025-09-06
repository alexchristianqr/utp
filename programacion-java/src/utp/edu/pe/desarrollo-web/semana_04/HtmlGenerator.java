package utils;

import java.io.PrintWriter;

public class HtmlGenerator {

    private final PrintWriter out;

    public HtmlGenerator(PrintWriter out) {
        this.out = out;
    }

    public void startHtml(String title) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }

    public void endHtml() {
        out.println("</body>");
        out.println("</html>");
    }

    public void h1(String text) {
        out.println("<h1>" + text + "</h1>");
    }

    public void p(String text) {
        out.println("<p>" + text + "</p>");
    }

    public void ul(Runnable content) {
        out.println("<ul>");
        content.run();
        out.println("</ul>");
    }

    public void li(String text) {
        out.println("<li>" + text + "</li>");
    }

    public void a(String href, String text) {
        out.println("<a href=\"" + href + "\">" + text + "</a>");
    }

    // Método para un formulario simple
    public void form(String action, String method, Runnable content) {
        out.println("<form action=\"" + action + "\" method=\"" + method + "\">");
        content.run();
        out.println("</form>");
    }

    public void input(String type, String placeholder, String name, String value) {
        out.println("<input type=\"" + type + "\" placeholder=\"" + placeholder + "\" name=\"" + name + "\" value=\"" + value + "\">");
    }

    public void button(String type, String text) {
        out.println("<button type=\"" + type + "\">" + text + "</button>");
    }
    
    public void table(Runnable content) {
        out.println("<table>");
        content.run();
        out.println("</table>");
    }
    
    public void tr(Runnable content) {
        out.println("<tr>");
        content.run();
        out.println("</tr>");
    }
    
    public void th(String text) {
        out.println("<th>" + text + "</th>");
    }
    
    public void td(String text) {
        out.println("<td>" + text + "</td>");
    }
}