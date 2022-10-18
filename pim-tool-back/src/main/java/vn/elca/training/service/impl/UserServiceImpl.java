package vn.elca.training.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.entity.Task;
import vn.elca.training.model.entity.User;
import vn.elca.training.repository.TaskRepository;
import vn.elca.training.repository.UserRepository;
import vn.elca.training.service.UserService;

import java.util.List;

/**
 * @author gtn
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public User findOne(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            Hibernate.initialize(user.getTasks());
            user = this.findOne(user.getUsername());
        }
        // Should throw exception if not found

        return user;
    }

    @Override
    public User findOne(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User addTasksToUser(List<Long> taskIds, String username) {
        List<Task> tasks = taskRepository.findAllById(taskIds);
        User user =  userRepository.findUserByUsername(username);

        tasks.forEach(task -> task.setUser(user));

        user.setTasks(tasks);

        return user;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
