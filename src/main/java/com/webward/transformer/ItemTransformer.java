package com.webward.transformer;

import com.webward.dto.ItemDto;
import com.webward.entities.Item;

/**
 * Created by dustinosthzn on 2014/11/16.
 */
public class ItemTransformer {

    public Item fromDto(ItemDto itemDTO)
    {
        if(itemDTO==null)
        {
            return null;
        }
        Item item = new Item();
        item.setName(itemDTO.getName());
        return item;
    }


    public ItemDto toDto(Item item)
    {
        if(item==null)
        {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        return itemDto;
    }
}
