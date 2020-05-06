package ch.jmildner.tools;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlTools
{

    public static void executeUpdate(Statement s, String sql, boolean... pause)
    {
        try
        {
            int numberOfRows = s.executeUpdate(sql);
            System.out.printf("SqlTools.executeUpdate (%-80s) numberOfRows: %d %n",
                    sql, numberOfRows);
        }
        catch (SQLException e)
        {
            printSQLException(sql, e, pause);
        }
    }

    public static void execute(Statement s, String sql, boolean... pause)
    {
        try
        {
            boolean retCode = s.execute(sql);
            System.out.printf("SqlTools.execute       (%-80s) retCode: %b %n", sql, retCode);
        }
        catch (SQLException e)
        {
            printSQLException(sql, e, pause);
        }
    }

    public static void select(Statement s, String sql, int... kza)
    {
        int kz = (kza.length > 0) ? kza[0] : 1;

        try
        {
            ResultSet rs = s.executeQuery(sql);
            System.out.printf("SqlTools.executeQuery  (%-80s) %n", sql);
            ResultSetMetaData md = rs.getMetaData();

            switch (kz)
            {
                case 1:
                    zeigeDaten(rs, md);
                    break;
                case 2:
                    zeigeMetadaten(md);
                    break;
                case 3:
                    zeigeMetadaten(md);
                    zeigeDaten(rs, md);
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e)
        {
            printSQLException(sql, e);
        }
    }

    public static void zeigeMetadaten(ResultSetMetaData md)
            throws SQLException
    {
        MyTools.uebOut("zeigeMetadaten() ", 1);

        for (int i = 1; i <= md.getColumnCount(); i++)
        {
            System.out.printf("%-21s ", md.getColumnLabel(i));
            System.out.printf("Type=%5d ", md.getColumnType(i));
            System.out.printf("TypeName=%-12s ", md.getColumnTypeName(i));
            System.out.printf("Precision=%5d ", md.getPrecision(i));
            System.out.printf("Scale=%5d ", md.getScale(i));
            System.out.printf("TableName=%-10s ", md.getTableName(i));
            System.out.printf("ColumnClassName=%-10s ", md.getColumnClassName(i));
            System.out.println();
        }
        System.out.println();
    }

    private static void zeigeDaten(ResultSet rs, ResultSetMetaData md)
            throws SQLException
    {
        MyTools.uebOut("zeigeDaten() ", 1);

        int anz = md.getColumnCount();

        for (int i = 1; i <= anz; i++)
        {
            System.out.printf("%-28S", md.getColumnLabel(i));
        }

        System.out.println();

        int zeileGeschrieben = 0;
        while (rs.next())
        {
            zeileGeschrieben++;
            for (int i = 1; i <= anz; i++)
            {
                Object o = rs.getObject(i);
                // System.out.println(o.getClass());
                System.out.printf("%-28s", o);
            }
            System.out.println();
        }
        if (zeileGeschrieben == 0)
        {
            System.out.println("keine Daten in Tabelle");
        }
        System.out.println();
        System.out.println();
    }

    private static void printSQLException(String sql, SQLException e, boolean... pause)
    {
        System.out.println("------------ SQLException ------------");
        System.out.println(sql);
        System.out.println(e.getMessage());
        System.out.println("------------ EXCEPTION ------------");

        if (pause.length == 1)
        {
            if (pause[0])
            {
                MyTools.pause();
            }
        }
        else
        {
            MyTools.pause();
        }
    }

}
