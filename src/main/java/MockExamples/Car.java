package MockExamples;

import java.util.ArrayList;

public interface Car {
    boolean needsFuel();
    double getEngineTemperature();
    double getEngineCapacity();
    void driveTo(String destination);
    ArrayList<String> visitedLocation();
}
