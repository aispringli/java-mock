package li.spring.redisson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author spring.li
 * @date 2019/11/09
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAmount {

    Integer id;

    String name;

    Integer money;

}
