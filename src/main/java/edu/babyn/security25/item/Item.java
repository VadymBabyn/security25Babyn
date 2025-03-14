package edu.babyn.security25.item;

/*
@author   vadim
@project   security25
@class  item
@version  1.0.0
@since 14.03.2025 - 11.51
*/

import lombok.*;
import org.springframework.data.annotation.Id;


import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Item {
    @Id
    private String id;
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getId(), item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
