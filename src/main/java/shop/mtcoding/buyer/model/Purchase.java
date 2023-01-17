package shop.mtcoding.buyer.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Purchase {
    private int id;
    private String userId;
    private int productId;
    private Timestamp createdAt;
}
