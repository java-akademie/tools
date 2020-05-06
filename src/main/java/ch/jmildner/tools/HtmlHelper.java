package ch.jmildner.tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public final class HtmlHelper
{

    public final static char QU = '"';
    public final static String BR = "<br />";
    public final static String HR = "<hr />";
    public final static String NBSP = "&nbsp;";
    public final static String STYLE = "http://localhost:8080/wcds1/nav/style.css";

    public final static String beginHtmlHeadBody(String title)
    {
        return beginHtmlHeadBody(title, STYLE);
    }

    public static String beginHtmlHeadBody(String title, String style)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n\n");
        sb.append("<html>\n\n");
        sb.append("<head>\n");
        sb.append("    <title>").append(title).append("</title>\n");
        sb.append("    <meta http-equiv=\"Content-Type\" "
                + "content=\"text/html; charset=UTF-8\" />" + "\n");
        sb.append("    <link ").append(pair("href", style)).append(pair("rel", "stylesheet"))
                .append(pair("type", "text/css")).append(" />\n");
        sb.append("</head>\n\n");
        sb.append("<body>\n");
        return sb.toString();
    }

    public static String endHtmlBody()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n</body>\n\n");
        sb.append("</html>\n\n");
        return (sb.toString());
    }

    public static String formBegin(int tiefe, String action,
            String method)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getLeerstellen(tiefe));
        sb.append("<form ");
        sb.append(pair("action", action));
        sb.append(pair("method", method));
        sb.append(">");
        return sb.toString() + "";
    }

    public static String input(int tiefe, String type, String name,
            String value, int size)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getLeerstellen(tiefe));
        sb.append("<input ");
        sb.append(pair("type", type));
        sb.append(pair("name", name));
        sb.append(pair("value", value));
        sb.append(pair("size", size));
        sb.append(" />");
        return sb.toString();
    }

    public static String input(int tiefe, String type, String name,
            String value)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getLeerstellen(tiefe));
        sb.append("<input ");
        sb.append(pair("type", type));
        sb.append(pair("name", name));
        sb.append(pair("value", value));
        sb.append(" />");
        return sb.toString();
    }

    public static String formEnd(int tiefe)
    {
        return getLeerstellen(tiefe) + "</form>";
    }

    public static String tableBegin(int tiefe, String... cls)
    {
        if (cls.length == 1)
        {
            return getLeerstellen(tiefe) + "<table class='" + cls[0]
                    + "'>";
        }
        else
        {
            return getLeerstellen(tiefe) + "<table>";
        }
    }

    public static String tableCaption(int tiefe, String ueberschrift,
            String... cls)
    {
        if (cls.length == 1)
        {
            return getLeerstellen(tiefe) + "<caption class='" + cls[0]
                    + "'>" + ueberschrift + "</caption>";
        }
        else
        {
            return getLeerstellen(tiefe) + "<caption>" + ueberschrift
                    + "</caption>";
        }
    }

    public static String thBlock(int level, String... string)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlHelper.trBegin(level, "header")).append("\n");
        for (String s : string)
        {
            sb.append(HtmlHelper.thRow(level + 2, s)).append("\n");
        }
        sb.append(HtmlHelper.trEnd(level)).append("\n");
        return sb.toString();
    }

    public static String tdBlock(int level, String... string)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlHelper.trBegin(level, "data")).append("\n");
        for (String s : string)
        {
            sb.append(HtmlHelper.tdRow(level + 2, s)).append("\n");
        }
        sb.append(HtmlHelper.trEnd(level)).append("\n");
        return sb.toString();
    }

    public static String trBegin(int tiefe, String... cls)
    {
        if (cls.length == 1)
        {
            return getLeerstellen(tiefe) + "<tr class='" + cls[0]
                    + "'>";
        }
        else
        {
            return getLeerstellen(tiefe) + "<tr>";
        }
    }

    public static String trEnd(int tiefe)
    {
        return getLeerstellen(tiefe) + "</tr>";
    }

    public static String thRow(int tiefe, String wert)
    {
        return getLeerstellen(tiefe) + "<th class='header'>" + wert
                + "</th>";
    }

    public static String tdRow(int tiefe, String wert)
    {
        return getLeerstellen(tiefe) + "<td class='data'>" + wert
                + "</td>";
    }

    public static String tableEnd(int tiefe)
    {
        return getLeerstellen(tiefe) + "</table>";
    }

    public static String pair(String name, int value)
    {
        return String.format(" %s=%c%d%c ", name, QU, value, QU);
    }

    public static String pair(String name, String value)
    {
        return String.format(" %s=%c%s%c ", name, QU, value, QU);
    }

    public static String trimAndFilter(String string)
    {
        if (string == null)
        {
            return "";
        }

        string = string.trim();

        StringBuilder filtered = new StringBuilder();

        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            switch (c)
            {
                case '<':
                    filtered.append("&lt;");
                    break;
                case '>':
                    filtered.append("&gt;");
                    break;
                case '"':
                    filtered.append("&quot;");
                    break;
                case '&':
                    filtered.append("&amp;");
                    break;
                default:
                    filtered.append(c);
            }

        }
        return filtered.toString();
    }

    public static String ueb(int level, String ueberschrift)
    {
        String h = level >= 1 && level <= 5 ? "h" + level : "h5";
        return String.format("<%s>%s</%s>%n", h, ueberschrift, h);
    }

    public static String selectedData(ResultSet rs)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(HtmlHelper.tableBegin(4, "data")).append("\n");
        sb.append(HtmlHelper.tableBegin(4, "data")).append("\n");
        sb.append(HtmlHelper.tableCaption(6, "die Daten", "data1")).append("\n");

        try
        {
            ResultSetMetaData md = rs.getMetaData();

            int anzahl = md.getColumnCount();

            String[] columnNames = new String[anzahl];
            String[] columnWerte = new String[anzahl];

            for (int i = 0; i < anzahl; i++)
            {
                columnNames[i] = md.getColumnName(i + 1);
            }

            sb.append(HtmlHelper.thBlock(6, columnNames)).append("\n");

            while (rs.next())
            {
                for (int i = 0; i < anzahl; i++)
                {
                    columnWerte[i] = rs.getString(columnNames[i]);
                }
                sb.append(HtmlHelper.tdBlock(6, columnWerte)).append("\n");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace(System.err);
        }

        sb.append(HtmlHelper.tableEnd(4)).append("\n");

        return sb.toString();
    }

    public static String makeText(int tiefe, String text)
    {
        return getLeerstellen(tiefe) + text;
    }

    private static String getLeerstellen(int tiefe)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= tiefe; i++)
        {
            sb.append(" ");
        }

        return sb.toString();
    }
}
