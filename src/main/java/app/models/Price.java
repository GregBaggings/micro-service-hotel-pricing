package app.models;

import javax.persistence.*;

/**
 * Created by Gergely_Agnecz on 8/1/2017.
 */
@Entity
@Table(name = "prices")
public class Price {

    @Column(name = "hotelid", nullable = false)
    private int hotelId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roomid", nullable = false)
    private int roomId;
    @Column(name = "price", nullable = false)
    private int price;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
