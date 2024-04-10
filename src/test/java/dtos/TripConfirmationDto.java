package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TripConfirmationDto {

    private Long driverId;
    private String driverName;
    private Double price;
    private String message;


    @Override
    public String toString() {
        return "TripConfirmationDto{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", price=" + price +
                ", message='" + message + '\'' +
                '}';
    }
}
