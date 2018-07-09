package com.lynx.impexp.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();

    void store(String requestId, byte[] bytes);

    byte[] getDocument(String username, String fileName);

}
