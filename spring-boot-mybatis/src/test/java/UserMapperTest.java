import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shaoyu Liu
 * @date 2023/6/11 13:51
 */
public class UserMapperTest {
    
    @Autowired
    private UserMapper userMapper;
    
    private void testInsert() {
        userMapper.insert(new Object());
    }
}
