package jp.co.jct.useThymeleaf;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryMessageRepository implements MessageRepository {

	// 用来模拟主键自增
	private static AtomicLong counter = new AtomicLong();

	// 用来存储消息
	private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<Long, Message>();

	@Override
	public Iterable<Message> findAll() {
		return this.messages.values();
	}

	@Override
	public Message save(Message message) {
		Long id = message.getId();
		if (id == null) {
			// 生成一个ID
			id = counter.incrementAndGet();
			message.setId(id);
		}
		// 保存消息
		this.messages.put(id, message);
		return message;
	}

	@Override
	public Message findMessage(Long id) {
		return this.messages.get(id);
	}

	@Override
	public void deleteMessage(Long id) {
		this.messages.remove(id);
	}
}