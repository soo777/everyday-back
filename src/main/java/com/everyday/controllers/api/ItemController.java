package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Files;
import com.everyday.model.Item;
import com.everyday.services.FilesService;
import com.everyday.services.ItemService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class ItemController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @Autowired
    private FilesService filesService;

    @Value("${filePathUrl}")
    private String filePathUrl;

    @GetMapping("/item")
    public ResponseEntity<APIResponse> getItemList(Authentication auth, @RequestParam int boardKey) {
        APIResponse rsp = null;

        logger.debug("auth1 - {}", auth);
        logger.debug("auth2 - {}", auth.getPrincipal().getClass());
        logger.debug("auth3 - {}", ((UserDetails) auth.getPrincipal()).getUsername());

        Object principal = auth.getPrincipal();

        String username = ((UserDetails) principal).getUsername();
        logger.debug("username - {}", username);
        logger.debug("password - {}", ((UserDetails) principal).getPassword());

        List<Item> itemList = itemService.getItemList(boardKey);

        rsp = new APIResponse(true, "success", itemList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/item")
    public ResponseEntity<APIResponse> addItem(Authentication auth, @RequestBody Item itemParam) {
        APIResponse rsp = null;

        String userId = ((UserDetails) auth.getPrincipal()).getUsername();

        logger.debug("@@@ param - {}", itemParam);

        Item item = new Item();
        item.setBoardKey(itemParam.getBoardKey());
        item.setContent(itemParam.getContent());
        item.setCreator(userId);
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

    /**
     * 게시글 업로드
     * @param boardKey
     * @param content
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/item/file")
    public ResponseEntity<APIResponse> uploadFile(Authentication auth,
                                                  @RequestParam int boardKey,
                                                  @RequestParam String content,
                                                  @RequestParam MultipartFile[] file) throws IOException {
        APIResponse rsp = null;

        logger.debug("@@@ boardKey - {}", boardKey);
        logger.debug("@@@ content - {}", content);
        logger.debug("@@@ param - {}", file);
        logger.debug("@@@ file length - {}", file.length);

        String userId = ((UserDetails) auth.getPrincipal()).getUsername();

        // item에 저장
        Item item = new Item();
        item.setBoardKey(boardKey);
        item.setContent(content);
        item.setCreator(userId);
        item.setStatus(true);
        item = itemService.addItem(item);

        logger.debug("item - {}", item);

        String fileName = "";
        String fileNameExt = "";
        String destFileName = "";

        for(int i=0; i<file.length; i++){
            logger.debug("@@@ file ori name - {}", file[i].getOriginalFilename());

            fileName = file[i].getOriginalFilename();
            fileNameExt = FilenameUtils.getExtension(fileName).toLowerCase();

            destFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileNameExt;
            logger.debug("@@@ fileName - {}", destFileName);

            File destFile = new File(filePathUrl + destFileName);
            file[i].transferTo(destFile);

            // 파일 path db 저장
            Files saveFile = new Files();
            saveFile.setItemKey(item.getItemKey());
            saveFile.setPath(filePathUrl + destFileName);
            filesService.addFile(saveFile);
        }

//        // local에 file 저장
//        // uuid 로 저장하도록
//        String fileName = file.getOriginalFilename();
//        String fileNameExt = FilenameUtils.getExtension(fileName).toLowerCase();
//
//        String destFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileNameExt;
//        logger.debug("@@@ fileName - {}", destFileName);
//        File destFile = new File(filePathUrl + destFileName);
//        file.transferTo(destFile);
//
//        // item에 저장
//        Item item = new Item();
//        item.setBoardKey(boardKey);
//        item.setContent(content);
//        item.setCreator("soo");
//        item.setStatus(true);
//        item = itemService.addItem(item);
//
//        logger.debug("item - {}", item);
//
//        // 파일 path db 저장
//        Files saveFile = new Files();
//        saveFile.setItemKey(item.getItemKey());
//        saveFile.setPath(filePathUrl + destFileName);
//        filesService.addFile(saveFile);

        rsp = new APIResponse(true, "update Board success", item);
        return ResponseEntity.ok(rsp);
    }


    /**
     * 업로드된 파일 이미지 로드
     * @param filePath
     * @return
     * @throws IOException
     */
    @GetMapping("/item/file")
    public ResponseEntity<APIResponse> getFileImage(@RequestParam String[] filePath) throws IOException {
        APIResponse rsp = null;

        logger.debug("filePath - {} ", filePath);

        Map map = new HashMap();
        List list = new ArrayList();

        for (int i = 0; i < filePath.length; i++) {
            logger.debug("filePath123 - {} ", filePath[i]);

            String fileExtName = filePath[i].substring(filePath[i].lastIndexOf(".") + 1);

            logger.debug("filePath - {} ", filePath);
            logger.debug("fileExtName - {} ", fileExtName);

            FileInputStream inputStream = null;
            ByteArrayOutputStream byteOutStream = null;

            File file = new File(filePath[i]);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
                byteOutStream = new ByteArrayOutputStream();

                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf)) != -1) {
                    byteOutStream.write(buf, 0, len);
                }

                byte[] fileArray = byteOutStream.toByteArray();
                String imageString = new String(Base64.encodeBase64(fileArray));

//                logger.debug("imageString - {} ", imageString);

                String img = "data:image/" + fileExtName + ";base64, " + imageString;

                list.add(img);
            }
        }
        map.put("img", list);

//        String fileExtName = filePath.substring(filePath.lastIndexOf(".") + 1);
//
//        logger.debug("filePath - {} ", filePath);
//        logger.debug("fileExtName - {} ", fileExtName);
//
//        FileInputStream inputStream = null;
//        ByteArrayOutputStream byteOutStream = null;
//
//        File file = new File(filePath);
//
//        Map map = new HashMap();
//
//        if(file.exists()) {
//            inputStream = new FileInputStream(file);
//            byteOutStream = new ByteArrayOutputStream();
//
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while((len = inputStream.read(buf)) != -1) {
//                byteOutStream.write(buf, 0, len);
//            }
//
//            byte[] fileArray = byteOutStream.toByteArray();
//            String imageString = new String(Base64.encodeBase64(fileArray));
//
//            logger.debug("imageString - {} ", imageString);
//
//            String img = "data:image/" + fileExtName +";base64, " + imageString;
//
//            map.put("img", img);
//        }

//        rsp = new APIResponse(true, "success", map);

        rsp = new APIResponse(true, "success", map);
        return ResponseEntity.ok(rsp);
    }

}
