import com.zeroc.Ice.Current;

public class ParkingI implements Demo.Parking  {


    @Override
    public void printString(String s, Current current) {
        System.out.println(s);
    }
}
