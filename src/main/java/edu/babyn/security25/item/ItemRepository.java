package edu.babyn.security25.item;

/*
@author   vadim
@project   security25
@class  itemRepository
@version  1.0.0
@since 14.03.2025 - 11.57
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ItemRepository extends MongoRepository<Item, String>
{

}
