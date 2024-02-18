package seminars.second.hw;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    List<Car> cars;
    List<Motorcycle> motorcycles;

    @BeforeEach
    void setUp() {
        cars = getCarItems();
        motorcycles = getMotorcycleItems();
    }


    public static List<Car> getCarItems() {
        List<Car> vehicles = new ArrayList<>();

        String[] vehicleCompanys = {"Toyota", "Lexus", "Volvo"};
        String[] vehicleModel = {"Corola", "RX450H", "V70"};
        int[] vehicleYear = {1999, 2010, 2005};

        for (int i = 0; i < vehicleCompanys.length; i++) {
            vehicles.add(new Car(vehicleCompanys[i], vehicleModel[i], vehicleYear[i]));

        }
        return vehicles;
    }

    public static List<Motorcycle> getMotorcycleItems() {
        List<Motorcycle> vehicles = new ArrayList<>();

        String[] vehicleCompanys = {"Yamaha", "Suzuki", "BMW"};
        String[] vehicleModel = {"YZF-R1", "Hayabusa", "S1000 RR"};
        int[] vehicleYear = {2000, 1999, 2008};

        for (int i = 0; i < vehicleCompanys.length; i++) {
            vehicles.add(new Motorcycle(vehicleCompanys[i], vehicleModel[i], vehicleYear[i]));
        }

        return vehicles;
    }

    /**
     * проверка того, что экземпляр объекта Car также является экземпляром
     * транспортного средства; (instanceof)
     */
    @Test
    void carInstanceVuhicle() {
        for (int i = 0; i < cars.size(); i++) {
            assertThat(cars.get(1) instanceof Vehicle);
        }
    }

    /**
     * проверка того, объект Car создается с 4-мя колесами
     */
    @Test
    void carCreatedWithFour(){
        for (int i = 0; i < cars.size(); i++) {
            assertThat(cars.get(i).getNumWheels()).isEqualTo(4);
        }
    }

    /**
     * проверка того, объект Motorcycle создается с 2-мя колесами
     */
    @Test
    void motorcycleCreatedWithTwo(){
        for (int i = 0; i < motorcycles.size(); i++) {
            assertThat(motorcycles.get(i).getNumWheels()).isEqualTo(2);
        }
    }

    /**
     * проверка того, объект Car развивает скорость 60
     * в режиме тестового вождения (testDrive())
     */
    @Test
    void carDevelopingSpeed(){
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).testDrive();
            assertThat(cars.get(i).getSpeed()).isEqualTo(60);
        }
    }

    /**
     * проверка того, объект Motorcycle развивает скорость 75
     * в режиме тестового вождения (testDrive())
     */
    @ParameterizedTest
    @ValueSource(ints = {75})
    void motorcycleDevelopingSpeed(int id){
        for (int i = 0; i < motorcycles.size(); i++) {
            motorcycles.get(i).testDrive();
            assertThat(motorcycles.get(i).getSpeed()).isEqualTo(id);
        }
    }

    /**
     * проверить, что в режиме парковки (сначала testDrive, потом park,
     * т.е эмуляция движения транспорта) машина останавливается (speed = 0)
     */
    @ParameterizedTest
    @ValueSource(ints = {0})
    void carMove(int id){
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).testDrive();
            cars.get(i).park();
            assertThat(cars.get(i).getSpeed()).isEqualTo(id);
        }
    }

    /**
     * проверить, что в режиме парковки (сначала testDrive, потом park  т.е эмуляция
     * движения транспорта) мотоцикл останавливается (speed = 0)
     */
    @ParameterizedTest
    @ValueSource(ints = {0})
    void motorcycleMove(int id){
        for (int i = 0; i < motorcycles.size(); i++) {
            motorcycles.get(i).testDrive();
            motorcycles.get(i).park();
            assertThat(motorcycles.get(i).getSpeed()).isEqualTo(id);
        }
    }
}