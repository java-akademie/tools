package ch.jmildner.tools_ee;

public class Debug
{

    private final String cls;
    private boolean debug;

    public Debug(String cls, boolean debug)
    {
        this.cls = cls;
        this.debug = debug;
    }

    public void debug(int x)
    {
        if (debug)
        {
            System.out.println(cls + "  " + x);
        }
    }

    public void debug(String x)
    {
        if (debug)
        {
            System.out.println(cls + "  " + x);
        }
    }

    public void setDebug(boolean debug)
    {
        this.debug = debug;
    }
}
