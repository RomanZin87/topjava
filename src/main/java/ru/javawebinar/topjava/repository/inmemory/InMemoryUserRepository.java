package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    {
        UserUtils.users.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return super.delete(id);
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return super.save(user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return super.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return super.getAll().stream().sorted(Comparator.comparing(AbstractNamedEntity::getName)).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return super.getAll().stream().filter(u->u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
