package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.service.INewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController(value = "newAPIOfAdmin")
public class NewAPI {
    @Autowired
    private INewService newService;

@PostMapping(value = "/api/new")
public NewDTO createNew(@RequestPart("test_file")MultipartFile file,
                        @RequestPart("test_json")NewDTO newDTO){
    System.out.println("Success");
    return newService.save(newDTO,file);

}

    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO updateNew) {
        NewDTO newDTO = newService.findById(updateNew.getId());
        newDTO.setId(updateNew.getId());
        newDTO.setTitle(updateNew.getTitle());
        newDTO.setCategoryCode(updateNew.getCategoryCode());
        newDTO.setContent(updateNew.getContent());
        newDTO.setShortDescription(updateNew.getShortDescription());
        return newService.update(newDTO);
    }

    @DeleteMapping(value = "/api/new")
    public void deleteNew(@RequestBody Long [] ids){
        newService.delete(ids);
    }
}
