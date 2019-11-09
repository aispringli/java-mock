package li.spring.mock.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichunying
 * @date 2019/10/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String username;

    String password;

    @Override
    public String toString() {
        Gson gson = new Gson();
        String result = gson.toJson(this);
        System.out.println("String: " + result);
        return result;
    }
}
