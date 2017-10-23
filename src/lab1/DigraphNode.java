package lab1;

public class DigraphNode
{
    String Words;
    DigraphNode Next;
    
    int Weight;
    int AdjPointNumber;
    boolean flag = false;

    public DigraphNode()
    {
        Next = null;
        AdjPointNumber = 0;
    }
}