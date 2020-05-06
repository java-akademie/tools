package ch.jmildner.mathe_tools;

public class Vector3Test
{

    public static void main(String[] args)
    {
        test1();

    }

    private static void test1()
    {
        Vector3D v1 = new Vector3D(3, 4, 5);
        Vector3D v2 = new Vector3D(3, 4, 5);

        Vector3D e1 = Vector3D.add(v1, v2);
        show("+", v1, v2, e1);

        Vector3D e2 = Vector3D.sub(e1, v2);
        show("-", e1, v2, e2);
        Vector3D e3 = Vector3D.sub(e2, v1);
        show("-", e2, v1, e3);

        e1 = Vector3D.einheitsVector(v1);
        show("einheitsVector", v1, e1);
    }

    private static void show(String s, Vector3D v1, Vector3D v2, Vector3D erg)
    {
        System.out.printf("[%s]  %s  [%s]  =   [%s] %n", v1, s, v2,
                erg);
    }

    private static void show(String s, Vector3D v1, Vector3D erg)
    {
        System.out.printf("[%s]  %s  [%s] %n", v1, s, erg);
    }

}
