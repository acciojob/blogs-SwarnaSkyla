package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        //set its attributes
        Blog blog= blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);

        return image;


    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image i=imageRepository2.findById(id).get();
        String dim=i.getDimensions();
        String[] sd=screenDimensions.split("X");
        String[] iD=dim.split("X");
        int sd1=Integer.parseInt(sd[0]);
        int sd2=Integer.parseInt(sd[1]);

        int iD1=Integer.parseInt(iD[0]);
        int iD2=Integer.parseInt(iD[1]);

        int ans=(sd1/iD1)*(sd2/iD2);
        return ans;


    }
}
