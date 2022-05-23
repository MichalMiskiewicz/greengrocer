package pl.miskiewiczmichal.greengrocerapi.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.miskiewiczmichal.greengrocerapi.jwt.JwtRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(authorities = "ROLE_Klient")
    public void getAllProductsTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    @WithMockUser(authorities = "ROLE_Admin")
    public void addNewProductTest() throws Exception {

        JsonObject productJson = new JsonObject();
        productJson.addProperty("name", "Testowy");
        productJson.addProperty("category", "Test");
        productJson.addProperty("description", "test");
        productJson.addProperty("price", "2");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products/add")
                        .content(productJson.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(404)).andReturn();
    }

    @Test
    @WithMockUser(authorities = "ROLE_Klient")
    public void updateProductsAmountTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/products/amount/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(403)).andReturn();
    }
}