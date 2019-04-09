package jp.co.jct.useThymeleaf;

public interface MessageRepository {
    Iterable<Message> findAll();
    Message save(Message message);
    Message findMessage(Long id);
    void deleteMessage(Long id);
}