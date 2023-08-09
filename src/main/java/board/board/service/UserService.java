package board.board.service;

import board.board.domain.User;
import board.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    // 리포지토리 연결
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public void join(User user){
        checkDuplicateId(user);
        checkPwd(user);
        userRepository.save(user);
    }

    // 아이디 중복 검사
    private void checkDuplicateId(User user){
        userRepository.findById(user.getId())
                .ifPresent(user1 -> {
                        throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
    }

    // 비밀번호 길이 검사
    private void checkPwd(User user){
        if ((user.getPassword()).length() < 4){
            throw new IllegalStateException("비밀번호는 4자 이상이어야 합니다.");
        }
    }

    // 로그인
    public boolean login(User user){
        Optional<User> logUser = userRepository.findById(user.getId());

        if (logUser.isPresent()){
            User savedUser = logUser.get();
            if(savedUser.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public Optional<User> findOne(String id){

        return userRepository.findById(id);
    }


}
