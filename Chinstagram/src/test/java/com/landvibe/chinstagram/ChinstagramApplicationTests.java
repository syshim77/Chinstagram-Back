package com.landvibe.chinstagram;


import com.landvibe.chinstagram.models.Content;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
class ChinstagramApplicationTests {

    @Test
    void contextLoads() {
        Content content = new Content();
        content.setScript("test script");
        System.out.println(content.getScript());
    }

}
