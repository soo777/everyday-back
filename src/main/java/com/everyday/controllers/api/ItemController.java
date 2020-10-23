package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Item;
import com.everyday.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public ResponseEntity<APIResponse> getItemList(@RequestParam int boardKey) {
        APIResponse rsp = null;

        List<Item> itemList = itemService.getItemList(boardKey);

        rsp = new APIResponse(true, "success", itemList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/item")
    public ResponseEntity<APIResponse> addItem(@RequestBody Item itemParam) {
        APIResponse rsp = null;

        logger.debug("@@@ param - {}", itemParam);

        Item item = new Item();
        item.setBoardKey(itemParam.getBoardKey());
        item.setContent(itemParam.getContent());
        item.setCreator("soo");
        item.setStatus(true);
        itemService.addItem(item);

        rsp = new APIResponse(true, "add Board success", item);
        return ResponseEntity.ok(rsp);
    }

    @DeleteMapping("/item")
    public ResponseEntity<APIResponse> deleteItem(@RequestParam int itemKey) {
        APIResponse rsp = null;

        logger.debug("@@@ param - {}", itemKey);

        Item item = itemService.getItem(itemKey);
        logger.debug("{}", item);

        item.setStatus(false);

        // item 삭제
        // itemService.deleteItem(itemKey);

        // item status 변경
        itemService.updateItem(item);

        rsp = new APIResponse(true, "delete Board success", null);
        return ResponseEntity.ok(rsp);
    }

    @PutMapping("/item")
    public ResponseEntity<APIResponse> updateItem(@RequestBody Item itemParam) {
        APIResponse rsp = null;

        logger.debug("@@@ param - {}", itemParam);

        Item item = itemService.getItem(itemParam.getItemKey());
        item.setContent(itemParam.getContent());
        itemService.updateItem(item);

        rsp = new APIResponse(true, "update Board success", item);
        return ResponseEntity.ok(rsp);
    }
}
