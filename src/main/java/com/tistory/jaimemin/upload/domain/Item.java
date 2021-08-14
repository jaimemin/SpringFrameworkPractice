package com.tistory.jaimemin.upload.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private Long id;

    private String itemName;

    private UploadFile attachedFile;

    private List<UploadFile> imageFiles;
}
