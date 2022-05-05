package pl.miskiewiczmichal.greengrocerapi.services;

import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.miskiewiczmichal.greengrocerapi.DTOs.AddProductDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.CategoryDTO;
import pl.miskiewiczmichal.greengrocerapi.DTOs.ProductDTO;
import pl.miskiewiczmichal.greengrocerapi.entities.Category;
import pl.miskiewiczmichal.greengrocerapi.entities.MeasureType;
import pl.miskiewiczmichal.greengrocerapi.entities.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    //@WithMockUser(authorities = "Role_Admin")
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

  /*  @Test
    //@WithMockUser(authorities = "Role_Admin")
    public void addNewProductTest() throws Exception {
        String username = "mdmis";
        String password = "pass";

        String body = "{\n" +
                "    \"username\": \"mdmis\",\n" +
                "    \"password\": \"pass\"\n" +
                "}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
                .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
//{"id":"d8c88d56-c653-11ec-9804-0242ac110002","userType":"Admin","username":"mdmis","token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtZG1pcyIsImV4cCI6MTY1MTYxMzU4NCwiaWF0IjoxNjUxNTk1NTg0fQ.cCYNBVjc-1FtnE93CjYJazzowuGE23Dgl1XbvUhAqCSwhD5hM1VZQO6Wr1UMq9T9nZrXsqgT7fQLEFtzkkki2w"}
        String str = Arrays.toString(response.split(","));
        //String token =

        final Matcher matcher = Pattern.compile("token").matcher(str);
        //if(matcher.find()){
         //   System.out.println(str.substring(matcher.end()).trim());
        //}

        System.out.println(matcher.group(1).toString());

        *//*JsonObject productJson = new JsonObject();
        productJson.addProperty("name","Testowy");
        productJson.addProperty("category","Test");
        productJson.addProperty("description","test");
        productJson.addProperty("price","2");



        mockMvc.perform(
                MockMvcRequestBuilders.post("/products/add")
                        .content(productJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(403)).andReturn();*//*

    }*/

    @Test
    //@WithMockUser(authorities = "Role_Admin")
    public void updateProductsAmount(){ //UUID productId, Integer productAmount

    }

    /*@Test
    //@WithMockUser(authorities = "Role_Admin")
    public void getCategoryTest() { //String categoryName
        mockMvc.perform()
    }*/

    @Test
    //@WithMockUser(authorities = "Role_Admin")
    public void getAllCategories(){

    }
}