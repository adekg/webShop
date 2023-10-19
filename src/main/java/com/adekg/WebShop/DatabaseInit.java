package com.adekg.WebShop;

import com.adekg.WebShop.model.Item;
import com.adekg.WebShop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DatabaseInit implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Autowired
    public DatabaseInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("Bluza z kapturem", new BigDecimal("119.99"), "https://premieresociety.com/1219-thickbox_default/flocking-hoodie-black.jpg"),
                new Item("Koszulka", new BigDecimal("59.99"), "https://bytom.com.pl/mis/m/czarny-t-shirt-bytom-110466.jpg"),
                new Item("Spodnie męskie", new BigDecimal("89.99"), "https://a.allegroimg.com/s360/1192a4/0a12cf71441cadd848534fe47352/Spodnie-meskie-jeans-W-36-94-cm-L-30-granatowe")
        ));
    }
}
