package com.lynx.impexp.storage.mainmemory;

import com.lynx.impexp.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MapStorageService implements StorageService {

    private Map<String, List<byte[]>> map;

    private String hashFunction(String x1, String x2){
        return  x1+x2;
    }

    @Autowired
    public MapStorageService() {
        this.map = new HashMap<>();
    }

    @Override
    public void init() {
        map.clear();
    }

    @Override
    public void store(String requestId, byte[] multipartFile) {
        if (!map.containsKey(requestId)){
            map.put(requestId, new ArrayList<>());
        }
        map.get(requestId).add(multipartFile);
    }

    @Override
    public byte[] getDocument(String requestId, String fileName) {
        System.out.println(map.containsKey(requestId));
        System.out.println(fileName);
        return map.get(requestId)
                /*.stream()
                .filter(multipartFile -> multipartFile.getOriginalFilename()!=fileName)
                .collect(Collectors.toList())*/
                .get(0);
    }

}
