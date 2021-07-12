package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)//읽기 에서 약간의 성능 향상 가능
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional//꼭 넣어줘야함!! saveIte는 쓰기인데 readOnly이면 읽기 전용이라 저장 안됨!
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
