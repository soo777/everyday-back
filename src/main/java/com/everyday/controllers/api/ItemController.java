package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Item;
import com.everyday.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ItemController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<APIResponse> getItemList() {
        APIResponse rsp = null;

        List<Item> itemList = itemService.getItemList();

        rsp = new APIResponse(true, "success", itemList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/item")
    public ResponseEntity<APIResponse> addItem(@RequestBody Item itemParam) {
        APIResponse rsp = null;

        logger.debug("@@@ param - {}", itemParam);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String nowDate = format.format(date);

        Item item = new Item();
        item.setBoardId(2);
        item.setContent(itemParam.getContent());
        item.setCreator("soo");
        item.setCreateDate(nowDate);
        item.setUpdateDate(nowDate);
        itemService.addItem(item);

        rsp = new APIResponse(true, "add Board success", item);
        return ResponseEntity.ok(rsp);
    }
}
