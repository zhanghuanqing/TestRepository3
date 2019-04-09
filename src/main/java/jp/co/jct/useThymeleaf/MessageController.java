package jp.co.jct.useThymeleaf;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/useThymeleaf")
public class MessageController {

	private final MessageRepository messageRepository;

	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	// 进入消息列表页面
	@GetMapping
	public ModelAndView list() {
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView("useThymeleaf/list", "messages", messages);
	}

	// 查看消息详情
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Message message) {
		return new ModelAndView("useThymeleaf/view", "message", message);
	}

	// 进入创建消息页面
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Message message) {
		return "useThymeleaf/form";
	}

	// 创建消息
	@PostMapping
	public ModelAndView create(@Valid Message message, BindingResult result, RedirectAttributes redirect) {
		// 内容验证
		if (result.hasErrors()) {
			return new ModelAndView("useThymeleaf/form", "formErrors", result.getAllErrors());
		}
		// 保存消息
		message = this.messageRepository.save(message);
		// 重定向增加一个消息
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/useThymeleaf/{message.id}", "message.id", message.getId());
	}

	// 删除消息
	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.messageRepository.deleteMessage(id);
		Iterable<Message> messages = this.messageRepository.findAll();
		return new ModelAndView("useThymeleaf/list", "messages", messages);
	}

	// 进入修改消息页面
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Message message) {
		return new ModelAndView("useThymeleaf/form", "message", message);
	}
}