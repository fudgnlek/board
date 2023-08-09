package board.board.repository;

import board.board.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository{

    private final Map<String, User> store = new HashMap<>();


    @Override
    public void save(User user) {
        store.put(user.getId(),user);
    }

    @Override
    public Optional<User> findById(String id) {
        return store.values().stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    public void clearStore(){
        store.clear();
    }
}
