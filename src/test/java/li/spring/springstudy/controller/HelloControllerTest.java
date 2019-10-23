package li.spring.springstudy.controller;

import li.spring.springstudy.model.User;
import li.spring.springstudy.service.UserService;
import li.spring.springstudy.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试常见的rest controller请求
 *
 * @author lichunying
 * @date 2019/10/23
 **/
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloController.class)
@ActiveProfiles(value = "test")
@IntegrationTest
@AutoConfigureWebMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    /**
     * get - 最基本请求
     *
     * @throws Exception
     */
    @Test
    void indexTest() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/hello?name=" + "me"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * get - 多参数
     *
     * @throws Exception
     */
    @Test
    void getUser() throws Exception {
        MvcResult mvcResult = this.mvc.perform(get("/hello/getUser?username={username}&password={password}", "username", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * post - url多参数
     *
     * @throws Exception
     */
    @Test
    void addUser() throws Exception {
        MvcResult mvcResult = this.mvc.perform(post("/hello/addUser?username={username}&password={password}", "username", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * post - body多参数
     *
     * @throws Exception
     */
    @Test
    void addUserBody() throws Exception {
        MvcResult mvcResult = this.mvc.perform(post("/hello/addUserBody")
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content("username=" + "username" + "&password=" + "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * post - body多参数且json格式
     *
     * @throws Exception
     */
    @Test
    void addUserBodyJson() throws Exception {
        User userResult = new User("我是返回结果", "123456");
        when(userService.addUser(any())).thenReturn(userResult);
        User user = new User("小李", "123456");
        MvcResult mvcResult = this.mvc.perform(post("/hello/addUserBodyJson", user).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name()).content(user.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}
